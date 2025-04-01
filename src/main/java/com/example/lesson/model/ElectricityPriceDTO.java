package com.example.lesson.model;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ElectricityPriceDTO {

    @DecimalMin("0.0")
    private double consumption;

    @DecimalMin("0.0")
    private double pricePerKWh;
}
