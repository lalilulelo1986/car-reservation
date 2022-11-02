package com.example.carreservation.domain;

import com.example.carreservation.domain.exception.ReservationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class CarReservationService {
    private final CarReservationRepository carReservationRepository;
    private final CarRepository carRepository;

    public CarReservationService(CarReservationRepository carReservationRepository, CarRepository carRepository) {
        this.carReservationRepository = carReservationRepository;
        this.carRepository = carRepository;
    }

    public List<Car> findAvailableCars(LocalDateTime fromTime, LocalDateTime toTime) {
        List<Car> allCars = carRepository.findAll();
        System.out.println("all: " + allCars);
        Set<Car> reservedByPeriod = carReservationRepository.findReservedByPeriod(fromTime, toTime);
        reservedByPeriod.forEach(it -> System.out.println(it.getId()));
        allCars.removeAll(reservedByPeriod);
        System.out.println("all: " + allCars);
        return allCars;
    }

    public List<Car> findAvailableCars2(LocalDateTime fromTime, LocalDateTime toTime) {
        return carRepository.findReservedCars(fromTime, toTime);
    }

    synchronized public CarReservation tryReserve(Long userId, Long carId, LocalDateTime fromTime, LocalDateTime toTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (fromTime.isBefore(currentDateTime) || fromTime.isAfter(currentDateTime.plusHours(24))) {
            throw new ReservationException("Reservation can be taken up to 24 hours ahead");
        }
        if (toTime.isAfter(fromTime.plusHours(2)))
            throw new ReservationException("Reservation duration can take up 2 hours");

        Set<Long> reservedIdsByPeriod = carReservationRepository.findReservedIdsByPeriod(fromTime, toTime);
        if (!reservedIdsByPeriod.contains(carId)) {
            Car car = carRepository.findById(carId);
            return carReservationRepository.saveReservation(car, userId, fromTime, toTime);
        }

        throw new ReservationException("No car available");
    }

    public List<CarReservation> findAll() {
        return carReservationRepository.findAll();
    }

    public CarReservation findById(Long id) {
        return carReservationRepository.findById(id);
    }
}
