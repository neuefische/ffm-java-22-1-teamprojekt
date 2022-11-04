package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static MockWebServer mockWebServer;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @DynamicPropertySource
    static void backendProperties(DynamicPropertyRegistry registry) {
        registry.add("weather.api.baseUrl", () -> mockWebServer.url("/").toString());
    }

    @AfterAll
    static void afterAll() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void fetchWeatherDateNullReturnsError() throws Exception {
        //given
        DailyWeather mockWeather = new DailyWeather(List.of(new WeatherData("dry", 21.7, 10, 2.2, 50)));
        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(mockWeather))
                .addHeader("Content-Type", "application/json")
        );
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/?date=" + null))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Date malformed"));
    }

    @Test
    void fetchWeatherForecastSuccessful() throws Exception {
        DailyWeather mockWeather = new DailyWeather(List.of(new WeatherData("dry", 21.7, 10, 2.2, 50)));
        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(mockWeather))
                .addHeader("Content-Type", "application/json")
        );
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/?date=" + Instant.now()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"wind_speed":2.2,
                        "cloud_cover":50,
                        "condition":"dry",
                        "temperature":21.7,
                        "sunshine":10}
                        """));
    }

    @Test
    void fetchWeatherWrongDateReturnsError() throws Exception {
        DailyWeather mockWeather = new DailyWeather(List.of(new WeatherData("dry", 21.7, 10, 2.2, 50)));
        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(mockWeather))
                .addHeader("Content-Type", "application/json")
        );
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/?date=abc"))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Date malformed"));
    }

    @Test
    void fetchWeatherBodyIsNullReturnsError() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
        );
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/?date="+ Instant.now()))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Response body is null"));
    }

    @Test
    void fetchWeatherWebserverIsUnreacheableReturnsError() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody("<?xml version=\"1.0\" encoding=\"UTF-8\"?><error><title>404 Not Found</title><description>No sources match your criteria</description></error>")
                .setStatus("HTTP/1.1 404")
                .addHeader("Content-Type", "application/xml")
        );
        Instant testDate = Instant.now().plus(30, ChronoUnit.DAYS);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/?date="+ testDate))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("404 Not Found from GET "
                        +mockWebServer.url("/")
                        +"?wmo_station_id=10637&date="
                        +OffsetDateTime.ofInstant(testDate, ZoneId.of("Europe/Berlin"))));
    }
}
