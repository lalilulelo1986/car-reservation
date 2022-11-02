package com.example.carreservation.domain.dto;

import javax.validation.constraints.NotBlank;

public class CarCreateDto {
    @NotBlank
    private final String make;
    @NotBlank
    private final String model;

    public CarCreateDto(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
}
