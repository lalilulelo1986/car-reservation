package com.example.carreservation.domain;

import java.time.LocalDateTime;

public class CarReservation {
    public Long getId() {
        return id;
    }

    public CarReservation(Long id, LocalDateTime fromTime, LocalDateTime toTime) {
        this.id = id;
//        this.car = car;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public void setId(Long id) {
        this.id = id;
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

    private Long id;

//    private Car car;

    private LocalDateTime fromTime;

    private LocalDateTime toTime;

//    public Car getCar() {
//        return car;
//    }
//
//    public void setCar(Car car) {
//        this.car = car;
//    }
}
