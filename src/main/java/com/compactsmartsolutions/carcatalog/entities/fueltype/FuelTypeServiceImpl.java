package com.compactsmartsolutions.carcatalog.entities.fueltype;

import com.compactsmartsolutions.carcatalog.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    private final FuelTypeRepository fuelTypeRepository;

    @Autowired
    public FuelTypeServiceImpl(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }

    @Override
    public FuelType createFuelType(FuelType fuelType) {
        return fuelTypeRepository.save(fuelType);
    }

    @Override
    public void deleteFuelType(Long id) {
        fuelTypeRepository.deleteById(id);
    }

    @Override
    public FuelType getFuelTypeById(Long id) {
        return fuelTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FuelType with id: " + id + " not found"));
    }

    @Override
    public FuelType getFuelTypeByName(String name) {
        FuelType fuelType;
        try {
            fuelType = fuelTypeRepository.findByName(name);
        } catch (EntityNotFoundException exception) {
            throw new EntityNotFoundException("FuelType with name: " + name + " not found");
        }
        return fuelType;
    }

    @Override
    public List<FuelType> getAllFuelTypes() {
        return fuelTypeRepository.findAll();
    }

}
