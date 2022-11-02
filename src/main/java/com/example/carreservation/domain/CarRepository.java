package com.example.carreservation.domain;

import com.example.carreservation.repo.CarEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface CarRepository {
    Car createCar(Car car);

    Car removeByNumber(String number);

    Car update(Car car);

    List<Car> findAll();

    Set<Long> findAllWithId();

    Car findById(Long id);

    List<Car> findReservedCars(LocalDateTime fromTime, LocalDateTime toTime);
}
