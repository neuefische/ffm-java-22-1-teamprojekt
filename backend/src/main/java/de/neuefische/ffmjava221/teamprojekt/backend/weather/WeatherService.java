package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Objects;


@Service
public class WeatherService {
    private final WebClient webClient;

    public WeatherService(@Value("${baseUrl}") String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public WeatherData fetchWeather(String date, int hour) throws IllegalArgumentException, NullPointerException, WebClientResponseException {
        if (hour < 0 || hour > 24) {
            throw new IllegalArgumentException("Hour must be between 0 and 24");
        }
        WeatherForecastResponse weatherResponse = Objects.requireNonNull(webClient
                        .get()
                        .uri("?wmo_station_id=10637&date=" + date)
                        .retrieve()
                        .toEntity(WeatherForecastResponse.class)
                        .block())//Optional().orElseThrow(() -> new NullPointerException("Fetch failed while waiting for Response"))
                        .getBody();

        if (weatherResponse != null) {
            return weatherResponse.weather().get(hour);
        } else {
            throw new NullPointerException("Response is empty/null");
        }
    }
}

