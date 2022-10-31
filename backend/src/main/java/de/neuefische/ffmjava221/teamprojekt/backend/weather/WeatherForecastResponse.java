package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import java.util.List;

public record WeatherForecastResponse(List<WeatherData> weather) {
}
