package com.example.carreservation.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarDto {
    private Long id;
    private String make;
    private String model;
    private String number;

    private CarDto() {
        super();
    }

    public CarDto(Long id, String make, String model, String number) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.number = number;
//        this.active = active;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }

    public Long getId() {
        return id;
    }
}
