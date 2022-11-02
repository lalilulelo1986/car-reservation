package com.example.carreservation.domain.repo;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.dto.CarUpdateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    void when_create_with_bad_number_then_throws() {
        // then
        assertThrows(ValidationException.class, () -> {
            // when
            carRepository.createCar(new Car("BMW", "725", "D0001", true));
        });
    }

    @Test
    void update() {
        // given
        Car car = carRepository.createCar(new Car("BMW", "725", "C0001", true));

        // when
        Car updatedCar = carRepository.update(car.getId(), new CarUpdateDto("Honda", "CRV"));

        // then
        assertEquals("Honda", updatedCar.getMake());
        assertEquals("CRV", updatedCar.getModel());

        // clean
        carRepository.delete(car.getId());
    }

    @Test()
    void when_update_NOTEXISTED_then_Nothing() {
        // when
        carRepository.updateActive(1001L, false);
    }

    @Test()
    void when_delete_EXISTED_then_ok() {
        // given
        Car car = carRepository.createCar(new Car("BMW", "725", "C334455", true));

        // when
        carRepository.updateActive(car.getId(), false);

        // then
        Car updatedCar = carRepository.findById(car.getId()).orElseThrow();
        assertEquals(false, updatedCar.getActive());

        // clean
        carRepository.delete(car.getId());
    }

    @Test
    void when_update_then_OK() {
        // given
        Car car = carRepository.createCar(new Car("BMW", "725", "C334457", true));

        // when
        Car updatedCar = carRepository.update(car.getId(), new CarUpdateDto("VAZ", "2023"));

        // then
        assertEquals("VAZ", updatedCar.getMake());
        assertEquals("2023", updatedCar.getModel());

        // clean
        carRepository.delete(car.getId());
    }
}