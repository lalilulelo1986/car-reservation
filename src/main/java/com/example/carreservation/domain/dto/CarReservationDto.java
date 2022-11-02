package com.example.carreservation.domain.dto;

import java.time.LocalDateTime;

public class CarReservationDto {
    private Long userId;
    private Long carId;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;

    public CarReservationDto(Long userId, Long carId, LocalDateTime fromTime, LocalDateTime toTime) {
        this.userId = userId;
        this.carId = carId;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalDateTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalDateTime toTime) {
        this.toTime = toTime;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
