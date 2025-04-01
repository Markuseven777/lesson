package com.example.lesson.controller;

import com.example.lesson.model.ElectricityPriceDTO;
import com.example.lesson.model.ElectricityResultDTO;
import com.example.lesson.service.ElectricityService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricityServiceTest {

    private final ElectricityService electricityService = new ElectricityService();

    @Test
    void shouldCalculateElectricityPrice() {
        ElectricityPriceDTO input = new ElectricityPriceDTO();
        input.setConsumption(2000);
        input.setPricePerKWh(0.3);

        ElectricityResultDTO result = electricityService.calculate(input);

        assertEquals(2000, result.getConsumption());
        assertEquals(0.3, result.getPricePerKWh());
        assertEquals(600.0, result.getPrice());
    }
}
