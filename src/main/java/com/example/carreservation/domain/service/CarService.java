package com.example.carreservation.domain.service;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.CarReservation;
import com.example.carreservation.domain.dto.CarActivateDto;
import com.example.carreservation.domain.dto.CarCreateDto;
import com.example.carreservation.domain.dto.CarUpdateDto;
import com.example.carreservation.domain.exception.ReservationException;
import com.example.carreservation.domain.exception.ResourceNotFoundException;
import com.example.carreservation.domain.repo.CarRepository;
import com.example.carreservation.repo.CarRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.example.carreservation.domain.Car.CAR_NUMBER_PREFIX;

@Service
@Transactional
public class CarService {
    static Logger LOGGER = LoggerFactory.getLogger(CarService.class);
    private final CarRepository carRepositoryImpl;
    private final CarReservationService carReservationService;
    private final CarSynchronizer carSynchronizer;

    public CarService(CarRepositoryImpl carRepositoryImpl, CarReservationService carReservationService, CarSynchronizer carSynchronizer) {
        this.carRepositoryImpl = carRepositoryImpl;
        this.carReservationService = carReservationService;
        this.carSynchronizer = carSynchronizer;
    }

    public Car findById(Long id) {
        Optional<Car> carOptional = carRepositoryImpl.findById(id);
        if (carOptional.isPresent() && carOptional.get().getActive()) {
            return carOptional.get();
        }
        throw new ResourceNotFoundException("CMN-100-RNF");
    }

    public Car createCar(CarCreateDto carCreateDto) {
        String carNumber = CAR_NUMBER_PREFIX + carRepositoryImpl.nextCarNumberLong();
        return carRepositoryImpl.createCar(new Car(carCreateDto.getMake(), carCreateDto.getModel(), carNumber, true));
    }

    public List<Car> findAll(Boolean onlyActive) {
        return carRepositoryImpl.findAll(onlyActive);
    }

    public void updateActiveStatus(Long carId, @Valid CarActivateDto carActivateDto) {
        synchronized (carSynchronizer.getLockObject(carId)) {
            carRepositoryImpl.findById(carId).ifPresent(it -> {
                if (it.getActive()) {
                    carRepositoryImpl.updateActive(carId, carActivateDto.getActive());
                }
            });
        }
    }

    public Car update(Long carId, @Valid CarUpdateDto carUpdateDto) {
        synchronized (carSynchronizer.getLockObject(carId)) {
            Optional<Car> carOptional = carRepositoryImpl.findById(carId);
            if (carOptional.isPresent()) {
                return carRepositoryImpl.update(carId, carUpdateDto);
            }
        }
        throw new ResourceNotFoundException("CMN-100-RNF");
    }

    public void delete(Long carId) {
        synchronized (carSynchronizer.getLockObject(carId)) {
            List<CarReservation> curReservations = carReservationService.findAllCurrentReservationsByCarId(carId);
            if (!curReservations.isEmpty())
                throw new ReservationException("CRS-100-104");

            carReservationService.deleteAllByCarId(carId);
            carRepositoryImpl.delete(carId);
        }
    }
}
