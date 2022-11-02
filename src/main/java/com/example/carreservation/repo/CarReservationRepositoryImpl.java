package com.example.carreservation.repo;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.CarReservation;
import com.example.carreservation.domain.CarReservationRepository;
import com.example.carreservation.domain.exception.ReservationException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CarReservationRepositoryImpl implements CarReservationRepository {
    private final CarReservationJpaRepository carReservationJpaRepository;
    private final CarJpaRepository carJpaRepository;

    public CarReservationRepositoryImpl(CarReservationJpaRepository carReservationJpaRepository, CarJpaRepository carJpaRepository) {
        this.carReservationJpaRepository = carReservationJpaRepository;
        this.carJpaRepository = carJpaRepository;
    }

    @Override
    public Set<Car> findReservedByPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        return carReservationJpaRepository.findReservedByPeriod(startTime, endTime).stream().map(CarEntity::toCar).collect(Collectors.toSet());
    }

    @Override
    public Set<Long> findReservedIdsByPeriod(LocalDateTime startTime, LocalDateTime endTime) {
        return carReservationJpaRepository.findReservedIdsByPeriod(startTime, endTime);
    }

    @Override
    public CarReservation saveReservation(Car car, Long userId, LocalDateTime startTime, LocalDateTime endTime) {
        CarEntity carEntity = carJpaRepository.findById(car.getId()).orElseThrow(() -> new ReservationException("No car with id: " + car.getId()));
        CarReservationEntity carReservationEntity = new CarReservationEntity();
        carReservationEntity.setCarEntity(carEntity);
        carReservationEntity.setFromTime(startTime);
        carReservationEntity.setToTime(endTime);
        carReservationEntity.setUserId(userId);
        return this.carReservationJpaRepository.save(carReservationEntity).toCarReservation();
    }

    @Override
    public List<CarReservation> findAll() {
        return carReservationJpaRepository.findAll().stream().map(CarReservationEntity::toCarReservation).collect(Collectors.toList());
    }

    @Override
    public CarReservation findById(Long id) {
        return carReservationJpaRepository.findById(id).orElseThrow(() -> new ReservationException("no such reservation")).toCarReservation();
    }
}
