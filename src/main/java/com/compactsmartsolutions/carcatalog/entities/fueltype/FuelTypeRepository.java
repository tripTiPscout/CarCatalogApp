package com.compactsmartsolutions.carcatalog.entities.fueltype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {

    FuelType findByName(String name);

}
