package com.compactsmartsolutions.carcatalog.entities.fueltype;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/fuel-types")
public class FuelTypeController {

    private final FuelTypeService fuelTypeService;

    @Autowired
    public FuelTypeController(FuelTypeService fuelTypeService) {
        this.fuelTypeService = fuelTypeService;
    }

    @PostMapping("/fuel-type")
    public ResponseEntity<FuelType> createFuelType(@RequestBody FuelType transmission) {
        FuelType createdFuelType = fuelTypeService.createFuelType(transmission);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFuelType);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteFuelType(@PathVariable("id") Long id) {
        fuelTypeService.deleteFuelType(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{id}")
    public ResponseEntity<FuelType> getFuelTypeById(@PathVariable("id") Long id) {
        FuelType fuelType = fuelTypeService.getFuelTypeById(id);
        return ResponseEntity.ok(fuelType);
    }

    @GetMapping("/{name}")
    public ResponseEntity<FuelType> getFuelTypeByName(@PathVariable("name") String name) {
        FuelType fuelType = fuelTypeService.getFuelTypeByName(name);
        return ResponseEntity.ok(fuelType);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FuelType>> getAllFuelTypes() {
        List<FuelType> fuelTypes = fuelTypeService.getAllFuelTypes();
        return ResponseEntity.ok(fuelTypes);
    }

}
