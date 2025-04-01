package com.example.lesson.controller;

import com.example.lesson.model.ElectricityPriceDTO;
import com.example.lesson.model.ElectricityResultDTO;
import com.example.lesson.service.ElectricityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {ElectricityPriceController.class})
public class ElectricityPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ElectricityService electricityService;

    @Test
    void shouldReturnElectricityPriceWithParameter() throws Exception {
        mockMvc.perform(get("/consumption/500/price/0.3"))
                .andExpect(status().isOk())
                .andExpect(content().string("the price is: 150.0"));
    }

    @Test
    void shouldReturnElectricityPrice() throws Exception {
        String json = """
                {
                  "consumption": 3000,
                  "pricePerKWh": 0.42
                }
                """;


        ElectricityResultDTO result = new ElectricityResultDTO(3000, 0.42);

        when(electricityService.calculate(any(ElectricityPriceDTO.class)))
                .thenReturn(result);

        mockMvc.perform(post("/electricityPrice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.consumption").value(3000.0))
                .andExpect(jsonPath("$.pricePerKWh").value(0.42))
                .andExpect(jsonPath("$.price").value(1260.0))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldReturnError() throws Exception {
        String json = """
                {
                  "consumption": "wrong",
                  "pricePerKWh": "input"
                }
                """;

        mockMvc.perform(post("/electricityPrice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
