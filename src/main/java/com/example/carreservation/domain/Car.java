package com.example.carreservation.domain;

import com.example.carreservation.domain.dto.CarDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Car {
    public final static String CAR_NUMBER_PREFIX = "C";

    private Long id = null;
    @NotNull
    private final String make;
    @NotNull
    private final String model;
    @Pattern(regexp = CAR_NUMBER_PREFIX + "[\\d]+")
    private final String number;
    private final Boolean active;

    public Car(Long id, String make, String model, String number, Boolean active) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.number = number;
        this.active = active;
    }

    public Car(String make, String model, String number, Boolean active) {
        this.make = make;
        this.model = model;
        this.number = number;
        this.active = active;
    }

    public Long getId() {
        return id;
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

    public CarDto toDto() {
        return new CarDto(id, this.make, this.model, this.getNumber());
    }

    public Boolean getActive() {
        return active;
    }
}
