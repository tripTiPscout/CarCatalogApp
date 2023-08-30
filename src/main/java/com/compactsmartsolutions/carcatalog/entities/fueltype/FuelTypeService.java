package com.compactsmartsolutions.carcatalog.entities.fueltype;

import java.util.List;

public interface FuelTypeService {

    FuelType createFuelType(FuelType fuelType);

    void deleteFuelType(Long id);

    FuelType getFuelTypeById(Long id);

    FuelType getFuelTypeByName(String name);

    List<FuelType> getAllFuelTypes();

}
