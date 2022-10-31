package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherMockWebServerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private static MockWebServer mockWebServer;

    @BeforeAll
    static void beforeAll() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @DynamicPropertySource
    static void backendProperties(DynamicPropertyRegistry registry) {
        registry.add("baseUrl", () -> mockWebServer.url("/").toString());
    }

    @AfterAll
    static void afterAll() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void fetchWeatherDateNullReturnsError() throws Exception {
        //given
        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
        );
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/2022-10-31?hour=20"))
                //then
                .andExpect(status().isNotFound());
    }
}
