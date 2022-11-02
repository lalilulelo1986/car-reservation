package com.example.carreservation.controller;

import com.example.carreservation.domain.CarReservation;
import com.example.carreservation.domain.dto.CarDto;
import com.example.carreservation.domain.service.CarReservationService;
import com.example.carreservation.domain.dto.CarReservationDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/car/reservation")
public class CarReservationController {
    private final CarReservationService carReservationService;

    public CarReservationController(CarReservationService carReservationService) {
        this.carReservationService = carReservationService;
    }

    @PostMapping
    CarReservationDto reserve(@RequestBody CarReservationDto carReservationDto) {
        return carReservationService.createReservation(
                carReservationDto.getUserId(),
                carReservationDto.getCarId(),
                carReservationDto.getFromTime(),
                carReservationDto.getToTime()
        ).toDto();
    }

    @GetMapping
    List<CarReservationDto> findAll() {
        return carReservationService.findAll().stream().map(CarReservation::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> delete(@PathVariable Long id) {
        carReservationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("available")
    List<CarDto> findAvailableCarsByPeriod(
            @RequestParam(name = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam(name = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime
    ) {
        return carReservationService.findAvailableCarsByPeriod(startTime, endTime);
    }

    @GetMapping("{id}")
    CarReservationDto findById(@PathVariable Long id) {
        return this.carReservationService.findById(id).toDto();
    }
}
