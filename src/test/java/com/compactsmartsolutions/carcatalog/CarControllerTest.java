package com.compactsmartsolutions.carcatalog;

import com.compactsmartsolutions.carcatalog.entities.car.Car;
import com.compactsmartsolutions.carcatalog.entities.car.CarController;
import com.compactsmartsolutions.carcatalog.entities.car.CarService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCar_shouldReturnCreatedCar() {
        Car car = new Car();
        when(carService.createCar(any(Car.class))).thenReturn(car);

        ResponseEntity<Car> response = carController.createCar(car);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(car, response.getBody());
    }

    @Test
    void updateCar_shouldReturnUpdatedCar() {
        Car mockCar = new Car();
        Long carId = 1L;

        Map<String, String> carUpdates = new HashMap<>();
        carUpdates.put("remarks", "is repainted");

        when(carService.getCarById(carId)).thenReturn(mockCar);
        when(carService.updateCar(mockCar, carUpdates)).thenReturn(mockCar);

        ResponseEntity<Car> response = carController.updateCar(carId, carUpdates);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCar, response.getBody());
    }

    @Test
    void updateCar_shouldReturnNotFoundForNonExistentCar() {
        Long carId = 1L;
        when(carService.getCarById(eq(carId))).thenReturn(null);

        ResponseEntity<Car> response = carController.updateCar(carId, null);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteCar_shouldReturnNoContent() {
        Long carId = 1L;
        doNothing().when(carService).deleteCar(eq(carId));

        ResponseEntity<Void> response = carController.deleteCar(carId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getCarById_shouldReturnCar() {
        Long carId = 1L;
        Car car = new Car();
        when(carService.getCarById(eq(carId))).thenReturn(car);

        ResponseEntity<Car> response = carController.getCarById(carId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(car, response.getBody());
    }

    @Test
    void getAllCars_shouldReturnListOfCars() {
        List<Car> cars = new ArrayList<>();
        when(carService.getAllCars()).thenReturn(cars);

        ResponseEntity<List<Car>> response = carController.getAllCars();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    void searchCars_shouldReturnListOfCars() {
        List<Car> cars = new ArrayList<>();
        when(carService.searchCarsByParams(any(BigDecimal.class), anyString(), anyString(), anyString(), any(LocalDate.class), anyString()))
                .thenReturn(cars);

        ResponseEntity<List<Car>> response = carController.searchCarsByParams(BigDecimal.valueOf(30000), "ModelZ", "BrandA",
                "Diesel", LocalDate.now(), "Manual");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    void searchCar_shouldReturnListOfCars() {
        BigDecimal price = new BigDecimal("20000");
        String model = "ModelA";
        String brand = "BrandX";
        String fuel = "Gasoline";
        LocalDate registration = LocalDate.of(2022, 1, 1);
        String transmission = "Automatic";

        List<Car> expectedCars = new ArrayList<>();
        expectedCars.add(new Car());
        expectedCars.add(new Car());

        when(carService.searchCar(price, model, brand, fuel, registration, transmission)).thenReturn(expectedCars);

        ResponseEntity<List<Car>> response = carController.searchCar(price, model, brand, fuel, registration, transmission);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCars, response.getBody());
    }

}
