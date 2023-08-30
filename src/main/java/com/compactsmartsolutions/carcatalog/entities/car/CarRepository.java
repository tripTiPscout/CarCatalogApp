package com.compactsmartsolutions.carcatalog.entities.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM car c " +
            "WHERE (c.price <= :price) " +
            "OR (c.model.name = :model) " +
            "OR (c.model.brand.name = :brand) " +
            "OR (c.fuelType.name = :fuel) " +
            "OR (c.regDate = :registration) " +
            "OR (c.transmission.name = :transmission) " +
            "ORDER BY c.price DESC")
    List<Car> searchCarsByParams(@Param("price") BigDecimal maxPrice,
                                 @Param("model") String modelName,
                                 @Param("brand") String brandName,
                                 @Param("fuel") String fuelTypeName,
                                 @Param("registration") LocalDate regDate,
                                 @Param("transmission") String transmissionName);

    @Query("SELECT c FROM car c " +
            "WHERE (:price IS NULL OR c.price <= :price) " +
            "AND (:model IS NULL OR c.model.name = :model) " +
            "AND (:brand IS NULL OR c.model.brand.name = :brand) " +
            "AND (:fuel IS NULL OR c.fuelType.name = :fuel) " +
            "AND (:registration IS NULL OR c.regDate = :registration) " +
            "AND (:transmission IS NULL OR c.transmission.name = :transmission) " +
            "ORDER BY c.price DESC")
    List<Car> searchCar(@Param("price") BigDecimal maxPrice,
                        @Param("model") String modelName,
                        @Param("brand") String brandName,
                        @Param("fuel") String fuelTypeName,
                        @Param("registration") LocalDate regDate,
                        @Param("transmission") String transmissionName);

}
