package com.example.carreservation.repo.jpa.entity;

import com.example.carreservation.domain.Car;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CarEntity {
    public CarEntity() {
    }

    public CarEntity(String make, String model, String number, Boolean active) {
        this.make = make;
        this.model = model;
        this.number = number;
        this.active = active;
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
    @Column
    Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
        return new Car(this.id, this.make, this.model, this.number, active);
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "make = " + make + ", " + "model = " + model + ", " + "number = " + number + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarEntity carEntity = (CarEntity) o;
        return id != null && Objects.equals(id, carEntity.id)
                && number != null && Objects.equals(number, carEntity.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
