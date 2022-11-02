package com.example.carreservation.domain.repo;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.CarReservation;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CarReservationRepository {
    Boolean isCarAvailableForPeriod(Long carId, @NonNull LocalDateTime startTime, @NonNull LocalDateTime endTime);

    List<CarReservation> findAllCurrentReservationsByCarId(Long carId, LocalDateTime current);

    CarReservation saveReservation(@NonNull Car car, Long userId, @NonNull LocalDateTime startTime, @NonNull LocalDateTime endTime);

    List<CarReservation> findAll();

    Optional<CarReservation> findById(Long id);

    void delete(Long id);

    void deleteAllByCarId(Long carId);
}
