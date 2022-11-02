package com.example.carreservation.repo;

import com.example.carreservation.domain.Car;
import com.example.carreservation.domain.dto.CarDto;
import com.example.carreservation.domain.dto.CarUpdateDto;
import com.example.carreservation.domain.repo.CarRepository;
import com.example.carreservation.repo.jpa.CarJpaRepository;
import com.example.carreservation.repo.jpa.entity.CarEntity;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CarRepositoryImpl implements CarRepository {
    private final CarJpaRepository carJpaRepository;

    public CarRepositoryImpl(CarJpaRepository carJpaRepository) {
        this.carJpaRepository = carJpaRepository;
    }

    @Override
    public Car createCar(@Valid Car car) {
        CarEntity carEntity = carJpaRepository.save(new CarEntity(car.getMake(), car.getModel(), car.getNumber(), car.getActive()));
        return carEntity.toCar();
    }

    @Override
    public Long nextCarNumberLong() {
        return carJpaRepository.nextNumber();
    }

    @Override
    public List<Car> findAll(Boolean onlyActive) {
        if (onlyActive)
            return carJpaRepository.findByActive(true).stream().map(CarEntity::toCar).collect(Collectors.toList());
        else
            return carJpaRepository.findAll().stream().map(CarEntity::toCar).collect(Collectors.toList());
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carJpaRepository.findById(id).map(CarEntity::toCar);
    }

    @Override
    public List<CarDto> findAvailableCarsByPeriod(LocalDateTime fromTime, LocalDateTime toTime) {
        return carJpaRepository.findAvailableCarsByPeriod(fromTime, toTime).stream().map(it ->
                new CarDto(
                        ((BigInteger) it[0]).longValue(),
                        (String) it[1],
                        (String) it[2],
                        (String) it[3]
                )
        ).collect(Collectors.toList());
    }

    @Override
    public String generateNumber() {
        return null;
    }

//    @Override
//    public Car delete(Long id) {
////        return carJpaRepository.delete(id, LocalDateTime.now());
////        carJpaRepository
//        CarEntity carEntity = findById(id); //findEntityById(id);
//        carEntity.setActive(false);
//        return carJpaRepository.save(carEntity).toCar();
//    }

    @Override
    public Car update(Long id, @Valid CarUpdateDto carUpdateDto) {
        CarEntity carEntity = carJpaRepository.findById(id).get();
        carEntity.setMake(carUpdateDto.getMake());
        carEntity.setModel(carUpdateDto.getModel());
        return carJpaRepository.save(carEntity).toCar();
    }

    @Override
    public void updateActive(Long id, boolean active) {
        carJpaRepository.findById(id).ifPresent(it -> {
            it.setActive(active);
            carJpaRepository.save(it);
        });
    }

    @Override
    public void delete(Long carId) {
        carJpaRepository.deleteById(carId);
    }
}
