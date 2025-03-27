package com.example.lesson.controller;

import com.example.lesson.model.ElectricityPriceDTO;
import com.example.lesson.model.ElectricityResultDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/")
    public String getMapping() {
        return "Hello Spring!";
    }

    @PostMapping("/electricityPrice")
    public ElectricityResultDTO electricityPrice(@RequestBody ElectricityPriceDTO data) {
        return new ElectricityResultDTO(data);
    }

    @GetMapping("/consumption/{v}/price/{p}")
    public String calculate(@PathVariable double v, @PathVariable double p) {
        return "the price is: " + v * p;
    }
}
