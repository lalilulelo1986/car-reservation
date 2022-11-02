package com.example.carreservation.domain.repo;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.CarReservation;
import com.example.carreservation.domain.dto.CarCreateDto;
import com.example.carreservation.domain.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarReservationRepositoryTest {
    @Autowired
    private CarReservationRepository carReservationRepository;
    @Autowired
    private CarService carService;

    @Test
    void saveReservation() {
        // given
        Car car = carService.createCar(new CarCreateDto("Kia", "Accent"));
        LocalDateTime startTime = LocalDateTime.now().plusMinutes(20);
        LocalDateTime endTime = LocalDateTime.now().plusMinutes(25);

        // when
        CarReservation carReservation = carReservationRepository.saveReservation(car, 1L, startTime, endTime);

        // then
        assertEquals(car.getId(), carReservation.getCarId());
        assertEquals(startTime, carReservation.getFromTime());
        assertEquals(endTime, carReservation.getToTime());

        // clean
        carReservationRepository.delete(carReservation.getId());
    }


    @Test
    void when_car_reserved_then_not_available() {
        // given
        Car car = carService.createCar(new CarCreateDto("Kia", "Accent"));
        LocalDateTime startTime = LocalDateTime.now().plusMinutes(20);
        LocalDateTime endTime = LocalDateTime.now().plusMinutes(25);
        CarReservation carReservation = carReservationRepository.saveReservation(car, 1L, startTime, endTime);

        // when
        Boolean isCarAvailable = carReservationRepository.isCarAvailableForPeriod(car.getId(), startTime, endTime);
        System.out.println(isCarAvailable);
        System.out.println(carReservation.getId());

        // then
        assertEquals(false, isCarAvailable);

        // clean
        carReservationRepository.delete(carReservation.getId());
        carService.delete(car.getId());
    }
}