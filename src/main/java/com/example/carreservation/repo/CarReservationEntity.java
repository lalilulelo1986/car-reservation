package com.example.carreservation.repo;

import com.example.carreservation.domain.CarReservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "car_reservation")
public class CarReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private Long userId;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", nullable = false)
    @JsonIgnore
    private CarEntity carEntity;

    @Column(nullable = false)
    private LocalDateTime fromTime;

    @Column(nullable = false)
    private LocalDateTime toTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalDateTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalDateTime toTime) {
        this.toTime = toTime;
    }

    public CarEntity getCarEntity() {
        return carEntity;
    }

    public void setCarEntity(CarEntity carEntity) {
        this.carEntity = carEntity;
    }

    public CarReservation toCarReservation() {
        return new CarReservation(this.getId(), this.getFromTime(), this.getToTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarReservationEntity that = (CarReservationEntity) o;
        return id != null && Objects.equals(id, that.id) && carEntity.number != null
                && Objects.equals(carEntity.number, that.carEntity.number);
    }

    @Override
    public int hashCode() {
        return carEntity.number.hashCode();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
