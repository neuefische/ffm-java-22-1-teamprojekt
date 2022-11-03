package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record DailyWeather(
        @JsonProperty("weather") List<WeatherData> hourlyWeather) {
}
