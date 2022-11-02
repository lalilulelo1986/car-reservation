package com.example.carreservation.domain.service;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.CarReservation;
import com.example.carreservation.domain.dto.CarActivateDto;
import com.example.carreservation.domain.dto.CarCreateDto;
import com.example.carreservation.domain.exception.ReservationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarReservationServiceTest {
    @Autowired
    CarReservationService carReservationService;
    @Autowired
    CarService carService;
    @Value("${app.reservation.duration}")
    Integer reservationDuration;
    @Value("${app.reservation.reservationHoursAhead}")
    Integer reservationHoursAhead;

    @Test
    void when_endTime_greater_than_startTime_then_exception() {
        LocalDateTime current = LocalDateTime.now();

        // then
        ReservationException ex = assertThrows(ReservationException.class, () -> {
            // when
            carReservationService.validateReservationPeriod(current, current.minusHours(1));
        });

        assertTrue(ex.getMessageCode().contains("CRS-100-100"));
    }

    @Test
    void when_duration_more_than_then_throws() {
        LocalDateTime current = LocalDateTime.now();

        // then
        ReservationException ex = assertThrows(ReservationException.class, () -> {
            // when
            carReservationService.validateReservationPeriod(current, current.plusHours(reservationDuration).plusHours(1));
        });

        assertTrue(ex.getMessage().contains("Reservation duration can take up"));
    }

    @Test
    void when_startDate_more_than_available_then_throws() {
        LocalDateTime start = LocalDateTime.now().plusHours(reservationHoursAhead + 1);

        // then
        ReservationException ex = assertThrows(ReservationException.class, () -> {
            // when
            carReservationService.validateReservationPeriod(start, start.plusHours(1));
        });

        assertTrue(ex.getMessage().contains("Reservation can be taken up to "));
    }

    @Test
    void when_reserve_nonExisted_car_then_throws() {
        assertThrows(ReservationException.class, () -> {
            carReservationService.createReservation(1L, 1L, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        });
    }

    @Test
    void when_reserve_Existed_car_then_OK() {
        // given
        Car car = carService.createCar(new CarCreateDto("Renault", "Sandero"));

        // when
        CarReservation carReservation = carReservationService.createReservation(1L, car.getId(), LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));

        // then
        assertEquals(car.getId(), carReservation.getCarId());

        // clean
        carReservationService.delete(carReservation.getId());
        carService.delete(car.getId());
    }
}