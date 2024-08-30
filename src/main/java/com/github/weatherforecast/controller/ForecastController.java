package com.github.weatherforecast.controller;

import com.github.weatherforecast.model.Weather;
import com.github.weatherforecast.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;

    @GetMapping("/forecast/{date}")
    public ResponseEntity<Weather> getWeather(@PathVariable String date) {
        Weather locationWithBestWeatherConditions = forecastService.getLocationWithBestWeatherConditions(date);
        return new ResponseEntity<>(locationWithBestWeatherConditions, HttpStatus.OK);
    }
}
