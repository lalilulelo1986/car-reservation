package com.example.carreservation.domain.repo;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.dto.CarDto;
import com.example.carreservation.domain.dto.CarUpdateDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Validated
public interface CarRepository {
    Car createCar(@Valid Car car);

    Long nextCarNumberLong();

    List<Car> findAll(Boolean onlyActive);

    Optional<Car> findById(Long id);

    List<CarDto> findAvailableCarsByPeriod(LocalDateTime fromTime, LocalDateTime toTime);

    String generateNumber();

    Car update(Long id, @Valid CarUpdateDto car);

    void updateActive(Long id, boolean active);

    void delete(Long carId);
}
