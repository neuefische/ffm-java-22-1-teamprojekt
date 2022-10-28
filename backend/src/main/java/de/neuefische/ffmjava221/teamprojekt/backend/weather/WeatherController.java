package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/today")
    public WeatherData getWeatherToday() {
        String currentDate = LocalDate.now().toString();
        int currentHour = Integer.parseInt(java.time.LocalTime.now().toString().split(":")[0]);
        try {
            return weatherService.fetchWeatherForecast(currentDate, currentHour);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{date}")
    public WeatherData getWeather(@PathVariable String date, @RequestParam int hour) {
        try {
            return weatherService.fetchWeatherForecast(date, hour);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }
}
