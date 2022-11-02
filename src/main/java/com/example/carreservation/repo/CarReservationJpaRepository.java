package com.example.carreservation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Set;

public interface CarReservationJpaRepository extends JpaRepository<CarReservationEntity, Long> {
    @Query("select distinct c.carEntity.id from CarReservationEntity c where ?1 < c.toTime and ?2 > c.fromTime")
    Set<Long> findReservedIdsByPeriod(@NonNull LocalDateTime startTime, @NonNull LocalDateTime endTime);

    @Query("select distinct c.carEntity from CarReservationEntity c where ?1 < c.toTime and ?2 > c.fromTime")
    Set<CarEntity> findReservedByPeriod(@NonNull LocalDateTime startTime, @NonNull LocalDateTime endTime);
}
