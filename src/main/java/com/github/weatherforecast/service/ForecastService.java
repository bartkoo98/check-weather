package com.github.weatherforecast.service;

import com.github.weatherforecast.model.Weather;
import com.github.weatherforecast.validation.DateValidationService;
import com.github.weatherforecast.weatherAPI.WeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForecastService {

    private final DateValidationService dateValidationService;
    private final WeatherClient weatherClient;

    public Weather getLocationWithBestWeatherConditions (String date) {
        return null;
    }
}
