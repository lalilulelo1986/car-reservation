package com.example.carreservation.domain;

import com.example.carreservation.domain.dto.CarReservationDto;

import java.time.LocalDateTime;

public class CarReservation {
    private final Long id;
    private final Long userId;
    private final Long carId;
    private final LocalDateTime fromTime;
    private final LocalDateTime toTime;

    public CarReservation(Long id, Long userId, Long carId, LocalDateTime fromTime, LocalDateTime toTime) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public Long getCarId() {
        return carId;
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public CarReservationDto toDto() {
        return new CarReservationDto(id, userId, carId, fromTime, toTime);
    }

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }
}
