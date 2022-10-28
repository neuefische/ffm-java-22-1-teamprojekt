package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import javax.validation.constraints.NotNull;

public record WeatherResponseElement(@NotNull WeatherData weather) {
}
