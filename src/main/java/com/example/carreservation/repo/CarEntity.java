package com.example.carreservation.repo;

import com.example.carreservation.domain.Car;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "car")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CarEntity {
    public CarEntity() {
    }

    public CarEntity(Long id, String make, String model, String number) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.number = number;
    }

    public CarEntity(String make, String model, String number) {
        this.make = make;
        this.model = model;
        this.number = number;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column
    String make;
    @Column
    String model;
    @NaturalId
    @Column
    String number;

    @OneToMany(mappedBy = "carEntity", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CarReservationEntity> carReservationEntities = new LinkedHashSet<>();

    public Set<CarReservationEntity> getCarReservationEntities() {
        return carReservationEntities;
    }

    public void setCarReservationEntities(Set<CarReservationEntity> carReservationEntities) {
        this.carReservationEntities = carReservationEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Car toCar() {
        return new Car(this.id, this.make, this.model, this.number,
                this.carReservationEntities.stream().map(CarReservationEntity::toCarReservation).collect(Collectors.toList())
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarEntity carEntity = (CarEntity) o;
        return number != null && Objects.equals(number, carEntity.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "make = " + make + ", " + "model = " + model + ", " + "number = " + number + ")";
    }
}
