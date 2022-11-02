package com.example.carreservation.controller;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.CarReservation;
import com.example.carreservation.domain.dto.CarActivateDto;
import com.example.carreservation.domain.dto.CarCreateDto;
import com.example.carreservation.domain.dto.CarUpdateDto;
import com.example.carreservation.domain.service.CarReservationService;
import com.example.carreservation.domain.service.CarService;
import com.example.carreservation.domain.dto.CarDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/car")
public class CarController {
    private final CarService carService;
    private final CarReservationService carReservationService;

    public CarController(CarService carService, CarReservationService carReservationService) {
        this.carService = carService;
        this.carReservationService = carReservationService;
    }

    @GetMapping("/{id}")
    public CarDto findById(@PathVariable Long id) {
        return carService.findById(id).toDto();
    }

    @GetMapping("/{id}/reservation")
    public List<CarReservation> findCarReservations(@PathVariable Long id) {
        return carReservationService.findAllCurrentReservationsByCarId(id);
    }

    @PostMapping
    public CarDto create(@RequestBody CarCreateDto carCreateDto) {
        return carService.createCar(carCreateDto).toDto();
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        carService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping({"/{id}/activate"})
    public ResponseEntity<Object> updateStatus(@PathVariable Long id, @RequestBody CarActivateDto carActivateDto) {
        carService.updateActiveStatus(id, carActivateDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public CarDto update(@PathVariable Long id, @RequestBody CarUpdateDto carUpdateDto) {
        return carService.update(id, carUpdateDto).toDto();
    }

    @GetMapping
    public List<CarDto> findAll() {
        return carService.findAll(true).stream().map(Car::toDto).collect(Collectors.toList());
    }
}
