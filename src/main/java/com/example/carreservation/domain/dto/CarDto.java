package com.example.carreservation.domain.dto;

import javax.validation.constraints.NotBlank;

public class CarDto {
    private Long id;
    @NotBlank
    private String make;
    @NotBlank
    private String model;
    @NotBlank
    private String number;

    private CarDto() {
        super();
    }

    public CarDto(Long id, String make, String model, String number) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.number = number;
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
