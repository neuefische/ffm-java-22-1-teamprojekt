package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class WeatherServiceTest {

    private static MockWebServer mockWebServer;
    private WeatherService weatherService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s",
                mockWebServer.getPort());
        weatherService = new WeatherService(baseUrl);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void fetchWeatherReturnsWeatherData() throws Exception {
        //given
        Instant date = Instant.now();
        // mocked response from Mockserver has to be WeatherResponseElement, because the API-server initially returns this type
        DailyWeather mockWeather = new DailyWeather(List.of(new WeatherData("dry", 21.7, 10, 2.2, 50)));
        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(mockWeather))
                .addHeader("Content-Type", "application/json")
        );

        //when
        WeatherData actual = weatherService.fetchWeather(date);
        WeatherData expected = mockWeather.hourlyWeather().get(0);
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        //then
        assertEquals(expected, actual);
        assertEquals("GET", recordedRequest.getMethod());
    }

}
