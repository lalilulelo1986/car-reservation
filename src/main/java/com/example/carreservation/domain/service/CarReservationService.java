package com.example.carreservation.domain.service;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.CarReservation;
import com.example.carreservation.domain.dto.CarDto;
import com.example.carreservation.domain.exception.ReservationException;
import com.example.carreservation.domain.exception.ResourceNotFoundException;
import com.example.carreservation.domain.repo.CarRepository;
import com.example.carreservation.domain.repo.CarReservationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class CarReservationService {
    private final CarReservationRepository carReservationRepositoryImpl;
    private final CarRepository carRepositoryImpl;
    private final CarSynchronizer carSynchronizer;
    private final Integer reservationDuration;
    private final Integer reservationHoursAhead;

    public CarReservationService(
            CarReservationRepository carReservationRepositoryImpl,
            CarRepository carRepositoryImpl,
            CarSynchronizer carSynchronizer, @Value("${app.reservation.duration}") Integer reservationDuration,
            @Value("${app.reservation.reservationHoursAhead}") Integer reservationHoursAhead
    ) {
        this.carReservationRepositoryImpl = carReservationRepositoryImpl;
        this.carRepositoryImpl = carRepositoryImpl;
        this.carSynchronizer = carSynchronizer;
        this.reservationDuration = reservationDuration;
        this.reservationHoursAhead = reservationHoursAhead;
    }

    public List<CarDto> findAvailableCarsByPeriod(LocalDateTime fromTime, LocalDateTime toTime) {
        validateReservationPeriod(fromTime, toTime);
        return carRepositoryImpl.findAvailableCarsByPeriod(fromTime, toTime);
    }

    public CarReservation createReservation(Long userId, Long carId, LocalDateTime fromTime, LocalDateTime toTime) {
        synchronized (carSynchronizer.getLockObject(carId)) {
            validateReservationPeriod(fromTime, toTime);

            Optional<Car> car$ = carRepositoryImpl.findById(carId);
            if (car$.isPresent() && car$.get().getActive())
                if (carReservationRepositoryImpl.isCarAvailableForPeriod(carId, fromTime, toTime))
                    return carReservationRepositoryImpl.saveReservation(car$.get(), userId, fromTime, toTime);
        }
        throw new ReservationException("CRS-100-103");
    }

    public List<CarReservation> findAll() {
        return carReservationRepositoryImpl.findAll();
    }

    public CarReservation findById(Long id) {
        Optional<CarReservation> carReservation$ = carReservationRepositoryImpl.findById(id);
        if (carReservation$.isPresent()) {
            return carReservation$.get();
        }
        throw new ResourceNotFoundException("CRS-RESOURCE_NOT_FOUND");
    }

    public void deleteAllByCarId(Long carId) {
        synchronized (carSynchronizer.getLockObject(carId)) {
            carReservationRepositoryImpl.deleteAllByCarId(carId);
        }
    }

    public void delete(Long reservationId) {
        carReservationRepositoryImpl.delete(reservationId);
    }

    public List<CarReservation> findAllCurrentReservationsByCarId(Long carId) {
        return carReservationRepositoryImpl.findAllCurrentReservationsByCarId(carId, LocalDateTime.now());
    }

    public void validateReservationPeriod(LocalDateTime fromTime, LocalDateTime toTime) {
        if (fromTime.isAfter(toTime)) throw new ReservationException("CRS-100-100");

        if (toTime.isAfter(fromTime.plusHours(reservationDuration)))
            throw new ReservationException("CRS-100-101", reservationDuration);

        LocalDateTime currentDateTime = LocalDateTime.now();
        if (fromTime.isBefore(currentDateTime) || fromTime.isAfter(currentDateTime.plusHours(reservationHoursAhead))) {
            throw new ReservationException("CRS-100-102", reservationHoursAhead);
        }
    }
}
