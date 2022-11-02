package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherData(
        String condition,
        double temperature,
        int sunshine,
        @JsonProperty("wind_speed") double windSpeed,
        @JsonProperty("cloud_cover") int cloudCover) {

}
