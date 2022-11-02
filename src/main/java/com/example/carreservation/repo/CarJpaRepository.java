package com.example.carreservation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CarJpaRepository extends JpaRepository<CarEntity, Long> {
    @Query("select c.id from CarEntity c")
    Set<Long> findAllWithId();

    Optional<CarEntity> findByNumber(String number);

    @Query("select c from CarEntity c " +
            " left join c.carReservationEntities carReservationEntities " +
            "ON (?1 < carReservationEntities.toTime and ?2 > carReservationEntities.fromTime)")
    List<CarEntity> findReservedCars(LocalDateTime fromTime, LocalDateTime toTime);
}
