package com.example.carreservation.domain.dto;

import javax.validation.constraints.NotBlank;

public class CarUpdateDto {
    @NotBlank
    private String make;
    @NotBlank
    private String model;

    private CarUpdateDto() {
        super();
    }

    public CarUpdateDto(String make, String model) {
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
