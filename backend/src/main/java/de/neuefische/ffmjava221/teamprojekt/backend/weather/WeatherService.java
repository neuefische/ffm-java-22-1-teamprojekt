package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WeatherService {
    private final WebClient webClient = WebClient.create();

    public WeatherData fetchWeather() throws ResponseStatusException {
        WeatherResponseElement weatherResponse = webClient
                .get()
                .uri("https://api.brightsky.dev/current_weather?wmo_station_id=10637")
                .retrieve()
                .toEntity(WeatherResponseElement.class)
                .blockOptional().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Fetch failed while waiting for Response"))
                .getBody();

        if (weatherResponse != null) {
            return weatherResponse.weather();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Response is empty/null");
        }
    }
}

