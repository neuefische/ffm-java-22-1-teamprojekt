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

//    public WeatherData fetchWeatherToday() throws ResponseStatusException {
//        WeatherTodayResponseElement weatherResponse = webClient
//                .get()
//                .retrieve()
//                .toEntity(WeatherTodayResponseElement.class)
//                .blockOptional().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        "Fetch failed while waiting for Response"))
//                .getBody();
//
//        if (weatherResponse != null) {
//            return weatherResponse.weather();
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Response is empty/null");
//        }
//    }

    public WeatherData fetchWeatherForecast(String date, int hour) {
        if (hour < 0 || hour > 24) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hour must be between 0 and 24");
        }

        WeatherForecastResponse weatherResponse = webClient
                .get()
                .uri("https://api.brightsky.dev/weather?wmo_station_id=10637&date=" + date)
                .retrieve()
                .toEntity(WeatherForecastResponse.class)
                .blockOptional().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Fetch failed while waiting for Response"))
                .getBody();

        if (weatherResponse != null) {
            return weatherResponse.weather().get(hour);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Response is empty/null");
        }
    }
}

