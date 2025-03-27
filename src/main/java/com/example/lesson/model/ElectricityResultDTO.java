package com.example.lesson.model;

import lombok.Getter;

@Getter
public class ElectricityResultDTO {

    private final double consumption;
    private final double pricePerKWh;
    private final double price;

    public ElectricityResultDTO(double consumption, double pricePerKWh) {
        this.consumption = consumption;
        this.pricePerKWh = pricePerKWh;
        this.price = consumption * pricePerKWh;
    }

    public ElectricityResultDTO(ElectricityPriceDTO dto) {
        this(dto.getConsumption(), dto.getPricePerKWh());
    }

}
