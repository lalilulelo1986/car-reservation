package com.example.carreservation.repo.jpa;

import com.example.carreservation.repo.jpa.entity.CarReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;

public interface CarReservationJpaRepository extends JpaRepository<CarReservationEntity, Long> {
    @Query("""
            select case when count(c.carEntity) = 0 then true else false end
              from CarReservationEntity c
             where c.carEntity.id = ?1 and ?2 < c.toTime and ?3 > c.fromTime
            """)
    Boolean isCarAvailableForPeriod(Long carId, @NonNull LocalDateTime startTime, LocalDateTime endTime);

    @Query("""
            select c from CarReservationEntity c
             where c.carEntity.id = ?1
               and c.toTime > ?2
               and c.carEntity.active = 1
            """)
    List<CarReservationEntity> findAllCurrentReservationsByCarId(Long id, LocalDateTime current);

    long deleteByCarEntity_Id(Long id);
}
