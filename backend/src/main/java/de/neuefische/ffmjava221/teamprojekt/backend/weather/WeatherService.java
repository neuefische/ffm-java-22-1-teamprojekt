package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WeatherService {
    private final WebClient webClient;

    public WeatherService(@Value("${baseUrl}") String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public WeatherData fetchWeather() throws ResponseStatusException {
        WeatherResponseElement weatherResponse = webClient
                .get()
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

