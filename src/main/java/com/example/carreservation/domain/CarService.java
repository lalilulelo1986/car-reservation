package com.example.carreservation.domain;

import com.example.carreservation.repo.CarRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@Validated
public class CarService {
    private final CarRepositoryImpl carRepository;

    public CarService(CarRepositoryImpl carRepository) {
        this.carRepository = carRepository;
    }

    public Car findById(Long id) {
        return carRepository.findById(id);
    }

    public Car createCar(@Valid Car car) {
        return carRepository.createCar(car);
    }

    public Set<Long> findAllWithId() {
        return carRepository.findAllWithId();
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
