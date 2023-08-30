package com.compactsmartsolutions.carcatalog.entities.car;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/car")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car createdCar = carService.createCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") Long id, @RequestBody Map<String, String> carUpdates) {
        Car car = carService.getCarById(id);
        Car updatedCar = carService.updateCar(car, carUpdates);
        if (updatedCar != null) {
            return ResponseEntity.ok(updatedCar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id) {
        Car car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Car>> searchCarsByParams(@RequestParam(required = false) BigDecimal price,
                                                        @RequestParam(required = false) String model,
                                                        @RequestParam(required = false) String brand,
                                                        @RequestParam(required = false) String fuel,
                                                        @RequestParam(required = false) @DateTimeFormat(iso =
                                                        DateTimeFormat.ISO.DATE) LocalDate registration,
                                                        @RequestParam(required = false) String transmission) {
        List<Car> cars = carService.searchCarsByParams(price, model, brand, fuel, registration, transmission);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/search/car")
    public ResponseEntity<List<Car>> searchCar(@RequestParam(required = false) BigDecimal price,
                                               @RequestParam(required = false) String model,
                                               @RequestParam(required = false) String brand,
                                               @RequestParam(required = false) String fuel,
                                               @RequestParam(required = false) @DateTimeFormat(iso =
                                                       DateTimeFormat.ISO.DATE) LocalDate registration,
                                               @RequestParam(required = false) String transmission) {
        List<Car> cars = carService.searchCar(price, model, brand, fuel, registration, transmission);
        return ResponseEntity.ok(cars);
    }

}
