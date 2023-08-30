package com.compactsmartsolutions.carcatalog.entities.car;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CarService {

    Car createCar(Car car);

    Car updateCar(Car car, Map<String, String> carUpdates);

    void deleteCar(Long id);

    Car getCarById(Long id);

    List<Car> getAllCars();

    List<Car> searchCarsByParams(BigDecimal maxPrice, String modelName, String brandName, String fuelTypeName,
                                 LocalDate regDate, String transmissionName);

    List<Car> searchCar(BigDecimal maxPrice, String modelName, String brandName, String fuelTypeName,
                        LocalDate regDate, String transmissionName);
}
