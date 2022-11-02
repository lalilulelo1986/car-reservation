package com.example.carreservation.controller;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.CarReservation;
import com.example.carreservation.domain.CarReservationService;
import com.example.carreservation.domain.dto.CarReservationDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/car-reservation")
public class CarReservationController {
    private final CarReservationService carReservationService;

    public CarReservationController(CarReservationService carReservationService) {
        this.carReservationService = carReservationService;
    }

    @PostMapping
    CarReservation reserve(@RequestBody CarReservationDto carReservationDto) {
        return carReservationService.tryReserve(carReservationDto.getUserId(), carReservationDto.getCarId(), carReservationDto.getFromTime(), carReservationDto.getToTime());
    }

    @GetMapping
    List<CarReservation> findAll() {
        return this.carReservationService.findAll();
    }

    @GetMapping("available")
    List<Car> findAvailableCars(
            @RequestParam(name = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam(name = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime
    ) {
        return this.carReservationService.findAvailableCars(startTime, endTime);
    }

    @GetMapping("reserved")
    List<Car> findReservedCars(
            @RequestParam(name = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam(name = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime
    ) {
        return this.carReservationService.findAvailableCars2(startTime, endTime);
    }

    @GetMapping("{id}")
    CarReservation findById(@PathVariable Long id) {
        return this.carReservationService.findById(id);
    }
}
