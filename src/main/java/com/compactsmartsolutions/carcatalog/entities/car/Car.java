package com.compactsmartsolutions.carcatalog.entities.car;

import com.compactsmartsolutions.carcatalog.entities.fueltype.FuelType;
import com.compactsmartsolutions.carcatalog.entities.model.Model;
import com.compactsmartsolutions.carcatalog.entities.transmission.Transmission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;

import lombok.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "car")
@Table(name = "cars")
public class Car {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "vin_number")
    private String vinNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id")
    private Model model;

    @NonNull
    @Column(name = "price")
    private BigDecimal price;

    @NonNull
    @Column(name = "reg_date")
    private LocalDate regDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fuel_type_id")
    private FuelType fuelType;

    @Column(name = "remarks")
    private String remarks;

}
