package com.example.carreservation.repo;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.CarRepository;
import com.example.carreservation.domain.exception.ReservationException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CarRepositoryImpl implements CarRepository {
    private final CarJpaRepository carJpaRepository;

    public CarRepositoryImpl(CarJpaRepository carJpaRepository) {
        this.carJpaRepository = carJpaRepository;
    }

    @Override
    public Car createCar(Car car) {
        CarEntity carEntity = carJpaRepository.save(new CarEntity(car.getMake(), car.getModel(), car.getNumber()));
        return carEntity.toCar();
    }

    @Override
    public Car removeByNumber(String number) {
        return null;
    }

    @Override
    public Car update(Car car) {
        return null;
    }

    @Override
    public List<Car> findAll() {
        return carJpaRepository.findAll().stream().map(CarEntity::toCar).collect(Collectors.toList());
    }

    @Override
    public Set<Long> findAllWithId() {
        return carJpaRepository.findAllWithId();
    }

    @Override
    public Car findById(Long id) {
        return carJpaRepository.findById(id).orElseThrow(() -> new ReservationException("Car not exists")).toCar();
    }

    public List<Car> findReservedCars(LocalDateTime fromTime, LocalDateTime toTime) {
        return carJpaRepository.findReservedCars(fromTime, toTime).stream().map(CarEntity::toCar).collect(Collectors.toList());
    }
}
