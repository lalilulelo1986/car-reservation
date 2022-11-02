package com.example.carreservation.repo.jpa;

import com.example.carreservation.repo.jpa.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CarJpaRepository extends JpaRepository<CarEntity, Long> {
    List<CarEntity> findByActive(Boolean active);

    @Query(value = """
               select c.id, c.make, c.model, c.number from car c
                 left join car_reservation r on r.car_id = c.id
                  and ?< r.to_time and ?> r.from_time
                where c.active = 1
                group by c.id
               having count(r.id) = 0
            """, nativeQuery = true)
    List<Object[]> findAvailableCarsByPeriod(LocalDateTime fromTime, LocalDateTime toTime);

    @Modifying
    @Query("""
            UPDATE from CarEntity c set active = 0
             where c.id = ?1
               and c.active = 1
               and c.id not in (select distinct r.id
                              from CarReservationEntity r
                             where r.toTime > ?2
                               and r.carEntity.id = ?1
                           )
             """)
    int delete(Long id, LocalDateTime currentLocalDateTime);


    @Query(value = "select nextval('seq_car_number') from dual", nativeQuery = true)
    Long nextNumber();
}
