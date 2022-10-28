package de.neuefische.ffmjava221.teamprojekt.backend.weather;

import javax.validation.constraints.NotNull;
import java.util.List;

public record WeatherForecastResponse(@NotNull List<WeatherData> weather) {
}
