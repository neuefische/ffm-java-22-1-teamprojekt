package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void fetchWeatherForecastSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/?date=" + Instant.now()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature", Matchers.isA(Double.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.condition").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.condition", Matchers.isA(String.class)));
    }

    @Test
    void fetchWeatherWrongDateReturnsError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/?date=abc"))
                .andExpect(status().isBadRequest());
    }
}
