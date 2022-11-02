package com.example.carreservation.domain;

import com.example.carreservation.repo.CarEntity;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface CarReservationRepository {
    Set<Car> findReservedByPeriod(@NonNull LocalDateTime startTime, @NonNull LocalDateTime endTime);

    Set<Long> findReservedIdsByPeriod(@NonNull LocalDateTime startTime, @NonNull LocalDateTime endTime);

    CarReservation saveReservation(@NonNull Car car, Long userId, @NonNull LocalDateTime startTime, @NonNull LocalDateTime endTime);

    List<CarReservation> findAll();

    CarReservation findById(Long id);
}
