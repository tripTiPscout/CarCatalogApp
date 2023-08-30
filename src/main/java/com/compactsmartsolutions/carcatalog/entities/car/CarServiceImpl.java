package com.compactsmartsolutions.carcatalog.entities.car;

import com.compactsmartsolutions.carcatalog.entities.brand.Brand;
import com.compactsmartsolutions.carcatalog.entities.brand.BrandRepository;
import com.compactsmartsolutions.carcatalog.entities.fueltype.FuelType;
import com.compactsmartsolutions.carcatalog.entities.fueltype.FuelTypeRepository;
import com.compactsmartsolutions.carcatalog.entities.model.Model;
import com.compactsmartsolutions.carcatalog.entities.model.ModelRepository;
import com.compactsmartsolutions.carcatalog.entities.transmission.Transmission;
import com.compactsmartsolutions.carcatalog.entities.transmission.TransmissionRepository;
import com.compactsmartsolutions.carcatalog.exceptions.MissingMandatoryFieldException;
import com.compactsmartsolutions.carcatalog.exceptions.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final ModelRepository modelRepository;

    private final TransmissionRepository transmissionRepository;

    private final FuelTypeRepository fuelTypeRepository;

    private final BrandRepository brandRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelRepository modelRepository,
                          TransmissionRepository transmissionRepository,
                          FuelTypeRepository fuelTypeRepository, BrandRepository brandRepository) {
        this.carRepository = carRepository;
        this.modelRepository = modelRepository;
        this.transmissionRepository = transmissionRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public Car createCar(Car car) {
        validateMandatoryFields(car);

        Transmission transmission = transmissionRepository.save(car.getTransmission());

        Model model = modelRepository.save(car.getModel());

        Brand brand = brandRepository.save(car.getModel().getBrand());

        FuelType fuelType = fuelTypeRepository.save(car.getFuelType());

        car.setModel(model);
        car.getModel().setBrand(brand);
        car.setTransmission(transmission);
        car.setFuelType(fuelType);

        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car carToUpdate, Map<String, String> carUpdates) {
        if (carUpdates.containsKey("modelName")) {
            String modelName = carUpdates.get("modelName");
            Model model = modelRepository.findByName(modelName);
            carToUpdate.setModel(model);
        }

        if (carUpdates.containsKey("transmissionName")) {
            String transmissionName = carUpdates.get("transmissionName");
            Transmission transmission = transmissionRepository.findByName(transmissionName);
            carToUpdate.setTransmission(transmission);
        }

        if (carUpdates.containsKey("fuelTypeName")) {
            String fuelTypeName = carUpdates.get("fuelTypeName");
            FuelType fuelType = fuelTypeRepository.findByName(fuelTypeName);
            carToUpdate.setFuelType(fuelType);
        }

        if (carUpdates.containsKey("vinNumber")) {
            carToUpdate.setVinNumber(carUpdates.get("vinNumber"));
        }

        if (carUpdates.containsKey("price")) {
            BigDecimal price = new BigDecimal(carUpdates.get("price"));
            carToUpdate.setPrice(price);
        }

        if (carUpdates.containsKey("regDate")) {
            LocalDate regDate = LocalDate.parse(carUpdates.get("regDate"));
            carToUpdate.setRegDate(regDate);
        }

        if (carUpdates.containsKey("remarks")) {
            carToUpdate.setRemarks(carUpdates.get("remarks"));
        }

        return carRepository.save(carToUpdate);
    }

    @Override
    public void deleteCar(Long id) {
        Car car = getCarById(id);
        carRepository.delete(car);
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car with id: " + id + " not found"));
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> searchCarsByParams(BigDecimal maxPrice, String modelName, String brandName, String fuelTypeName,
                                        LocalDate regDate, String transmissionName) {
        return carRepository.searchCarsByParams(maxPrice, modelName, brandName, fuelTypeName, regDate, transmissionName);
    }

    @Override
    public List<Car> searchCar(BigDecimal maxPrice, String modelName, String brandName, String fuelTypeName,
                               LocalDate regDate, String transmissionName) {
        return carRepository.searchCar(maxPrice, modelName, brandName, fuelTypeName, regDate, transmissionName);
    }

    private void validateMandatoryFields(Car car) {
        if (car.getVinNumber() == null ||
                car.getModel() == null ||
                car.getPrice() == null ||
                car.getRegDate() == null ||
                car.getTransmission() == null ||
                car.getFuelType() == null) {
            throw new MissingMandatoryFieldException("Mandatory fields are missing");
        }
    }

}
