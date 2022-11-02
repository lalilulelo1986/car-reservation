package com.example.carreservation.repo;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.CarReservation;
import com.example.carreservation.domain.repo.CarReservationRepository;
import com.example.carreservation.repo.jpa.CarJpaRepository;
import com.example.carreservation.repo.jpa.CarReservationJpaRepository;
import com.example.carreservation.repo.jpa.entity.CarEntity;
import com.example.carreservation.repo.jpa.entity.CarReservationEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    public Boolean isCarAvailableForPeriod(Long carId, LocalDateTime startTime, LocalDateTime endTime) {
        return carReservationJpaRepository.isCarAvailableForPeriod(carId, startTime, endTime);
    }

    @Override
    public List<CarReservation> findAllCurrentReservationsByCarId(Long carId, LocalDateTime current) {
        return carReservationJpaRepository.findAllCurrentReservationsByCarId(carId, current)
                .stream().map(CarReservationEntity::toCarReservation).collect(Collectors.toList());
    }

    @Override
    public CarReservation saveReservation(Car car, Long userId, LocalDateTime startTime, LocalDateTime endTime) {
        CarEntity carEntity = carJpaRepository.findById(car.getId()).orElseThrow(() -> new IllegalArgumentException("No car with id: " + car.getId()));
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
    public Optional<CarReservation> findById(Long id) {
        return carReservationJpaRepository.findById(id).map(CarReservationEntity::toCarReservation);
    }

    @Override
    public void delete(Long id) {
        carReservationJpaRepository.findById(id).ifPresent(
                it -> carReservationJpaRepository.deleteById(it.getId())
        );
    }

    @Override
    public void deleteAllByCarId(Long carId) {
        carReservationJpaRepository.deleteByCarEntity_Id(carId);
    }
}
