package com.example.lesson.controller;

import com.example.lesson.model.ElectricityPriceDTO;
import com.example.lesson.model.ElectricityResultDTO;
import com.example.lesson.service.ElectricityService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ElectricityPriceController {

    private ElectricityService electricityService;

    @GetMapping("/")
    public String getMapping() {
        return "Hello Spring!";
    }

    @PostMapping("/electricityPrice")
    public ElectricityResultDTO calculate(ElectricityPriceDTO input) {
        if (input.getConsumption() <= 0 || input.getPricePerKWh() <= 0) {
            throw new IllegalArgumentException("Consumption and price must be greater than 0.");
        }
        return electricityService.calculate(input);
    }


    @GetMapping("/consumption/{v}/price/{p}")
    public String calculate(@PathVariable double v, @PathVariable double p) {
        return "the price is: " + v * p;
    }
}
