package com.example.carreservation.domain.dto;

import javax.validation.constraints.NotBlank;

public class CarCreateDto {
    @NotBlank
    private String make;
    @NotBlank
    private String model;

    private CarCreateDto() {
        super();
    }

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
