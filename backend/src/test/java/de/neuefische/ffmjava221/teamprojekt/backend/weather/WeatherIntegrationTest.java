package de.neuefische.ffmjava221.teamprojekt.backend.weather;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void fetchWeatherTodaySuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/today"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature", Matchers.isA(Double.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.condition").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.condition", Matchers.isA(String.class)));
    }

    @Test
    void fetchWeatherForecastSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/2022-10-31?hour=1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature", Matchers.isA(Double.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.condition").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.condition", Matchers.isA(String.class)));
    }

    @Test
    void fetchWeatherWrongDateReturnsError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/abc?hour=1"))
                .andExpect(status().isBadRequest());
    }
    @Test
    void fetchWeatherWrongHourReturnsError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/2022-10-31?hour=25"))
                .andExpect(status().isBadRequest());
    }
}
