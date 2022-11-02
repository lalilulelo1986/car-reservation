package com.example.carreservation.domain;

import com.example.carreservation.domain.dto.CarDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

public class Car {
    private Long id = null;
    @NotBlank
    private final String make;
    @NotBlank
    private final String model;
    @Pattern(regexp = "C[\\d]+")
    private String number;
    private List<CarReservation> carReservation;

    public Car(Long id, String make, String model, String number, List<CarReservation> carReservation) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.number = number;
        this.carReservation = carReservation;
    }

    public Car(String make, String model, String number, List<CarReservation> carReservation) {
        this.make = make;
        this.model = model;
        this.number = number;
        this.carReservation = carReservation;
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

    public List<CarReservation> getCarReservation() {
        return carReservation;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCarReservation(List<CarReservation> carReservation) {
        this.carReservation = carReservation;
    }
}
