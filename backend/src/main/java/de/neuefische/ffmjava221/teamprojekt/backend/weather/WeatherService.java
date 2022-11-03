package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;


@Service
public class WeatherService {
    private final WebClient webClient;

    public WeatherService(@Value("${weather.api.baseUrl}") String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public WeatherData fetchWeather(Instant date) {
        if (date == null) {
            throw new DateTimeException("Malformed date");
        } else {
            ResponseEntity<DailyWeather> weatherResponse = webClient
                    .get()
                    .uri("?wmo_station_id=10637&date=" + OffsetDateTime.ofInstant(date, ZoneId.of("Europe/Berlin")))
                    .retrieve()
                    .toEntity(DailyWeather.class)
                    .block();
            DailyWeather responseBody;
            if (weatherResponse != null) {
                responseBody = weatherResponse.getBody();
            } else {
                throw new WeatherResponseException("Weather response is null");
            }
            if (responseBody != null) {
                return responseBody.hourlyWeather().get(0);
            } else {
                throw new WeatherResponseException("Response body is null");
            }
        }
    }
}

