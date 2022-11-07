package com.example.carreservation.domain.dto;

import javax.validation.constraints.NotNull;

public class CarActivateDto {
    @NotNull
    private Boolean active;

    private CarActivateDto() {
        super();
    }

    public CarActivateDto(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }
}
