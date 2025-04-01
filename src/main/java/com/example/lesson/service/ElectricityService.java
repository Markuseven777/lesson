package com.example.lesson.service;

import com.example.lesson.model.ElectricityPriceDTO;
import com.example.lesson.model.ElectricityResultDTO;
import org.springframework.stereotype.Service;

@Service
public class ElectricityService {

    public ElectricityResultDTO calculate(ElectricityPriceDTO input) {
        return new ElectricityResultDTO(input);
    }
}
