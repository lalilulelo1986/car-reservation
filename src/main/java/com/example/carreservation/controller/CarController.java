package com.example.carreservation.controller;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.CarService;
import com.example.carreservation.domain.dto.CarDto;
import com.example.carreservation.repo.CarJpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService, CarJpaRepository carJpaRepository) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public Car findById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @PostMapping
    public CarDto create(@RequestBody CarDto carDto) {
        return carService.createCar(new Car(carDto.getMake(), carDto.getModel(), carDto.getNumber(), null)).toDto();
    }

    @GetMapping
    public List<Car> findAll() {
        return carService.findAll();
    }
}
