package de.neuefische.ffmjava221.teamprojekt.backend.weather;

public class WeatherResponseIsNullException extends RuntimeException{
    public WeatherResponseIsNullException(String message) {
        super(message);
    }
}
