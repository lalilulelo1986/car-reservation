package com.example.carreservation.domain.dto;

import javax.validation.constraints.NotNull;

public class CarActivateDto {
    @NotNull
    private final Boolean active;

    public CarActivateDto(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }
}
