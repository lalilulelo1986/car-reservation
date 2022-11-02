package com.example.carreservation.domain.dto;

public class CarDto {
    private Long id;
    private String make;
    private String model;
    private String number;

    public CarDto(Long id, String make, String model, String number) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.number = number;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
