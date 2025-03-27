package com.example.lesson.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(controllers = {HelloController.class})
public class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

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
                      "consumption": wrong,
                      "pricePerKWh": input
                    }
                """;

        mockMvc.perform(post("/electricityPrice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
