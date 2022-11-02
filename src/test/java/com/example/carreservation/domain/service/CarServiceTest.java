package com.example.carreservation.domain.service;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.CarReservation;
import com.example.carreservation.domain.dto.CarCreateDto;
import com.example.carreservation.domain.dto.CarUpdateDto;
import com.example.carreservation.domain.exception.ReservationException;
import com.example.carreservation.domain.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CarServiceTest {
    @Autowired
    CarService carService;
    @Autowired
    CarReservationService carReservationService;

    @Test
    void when_delete_with_currently_reserved_then_throws() {
        // given
        Car car = carService.createCar(new CarCreateDto("Audi", "200"));
        LocalDateTime now = LocalDateTime.now();
        CarReservation reservation = carReservationService.createReservation(
                1L, car.getId(), now.plusMinutes(60L), now.plusMinutes(100L)
        );

        // then
        assertThrows(ReservationException.class, () -> {
            // when
            carService.delete(car.getId());
        });

        // clean
        carReservationService.delete(reservation.getId());
        carService.delete(car.getId());
    }

    @Test
    void when_update_not_existed_then_throws_not_found() {
        // then
        assertThrows(ResourceNotFoundException.class, () -> {
            // when
            carService.update(30000L, new CarUpdateDto("audi", "200"));
        });
    }
}