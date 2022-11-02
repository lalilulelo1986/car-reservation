package com.example.carreservation.domain.dto;

import java.time.LocalDateTime;

public class CarReservationDto {
    private final Long id;
    private final Long userId;
    private final Long carId;
    private final LocalDateTime fromTime;
    private final LocalDateTime toTime;

    public CarReservationDto(Long id, Long userId, Long carId, LocalDateTime fromTime, LocalDateTime toTime) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public Long getCarId() {
        return carId;
    }

    public Long getId() {
        return id;
    }
}
