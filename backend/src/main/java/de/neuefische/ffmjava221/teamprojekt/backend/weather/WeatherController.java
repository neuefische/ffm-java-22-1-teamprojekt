package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.time.DateTimeException;
import java.time.Instant;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public WeatherData getWeather(@RequestParam String date) {
        try {
            Instant dateAsInstant = Instant.parse(date);
            return weatherService.fetchWeather(dateAsInstant);
        } catch (WeatherResponseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DateTimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date malformed");
        } catch (WebClientResponseException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage());
        }
    }
}
