package com.example.carreservation.domain.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CarReservationDto {
    private Long id;
    @NotBlank
    private Long userId;
    @NotBlank
    private Long carId;
    @NotBlank
    private LocalDateTime fromTime;
    @NotBlank
    private LocalDateTime toTime;

    private CarReservationDto() {
        super();
    }

    public CarReservationDto(Long id, Long userId, Long carId, LocalDateTime fromTime, LocalDateTime toTime) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public Long getCarId() {
        return carId;
    }

    public Long getId() {
        return id;
    }
}
