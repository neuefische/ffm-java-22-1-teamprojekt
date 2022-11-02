package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class WeatherService {
    private final WebClient webClient;

    public WeatherService(@Value("${baseUrl}") String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public WeatherData fetchWeather(String date, int hour) {
        if (hour < 0 || hour > 24) {
            throw new IllegalArgumentException("Hour must be between 0 and 24");
        }
        ResponseEntity<WeatherForecastResponse> weatherResponse = webClient
                .get()
                .uri("?wmo_station_id=10637&date=" + date)
                .retrieve()
                .toEntity(WeatherForecastResponse.class)
                .block();

        WeatherForecastResponse responseBody;

        if (weatherResponse != null) {
            responseBody = weatherResponse.getBody();
        } else {
            throw new WeatherResponseException("Weather response is null");
        }
        if (responseBody != null) {
            return responseBody.weather().get(hour);
        } else {
            throw new WeatherResponseException("Response body is null");
        }
    }
}

