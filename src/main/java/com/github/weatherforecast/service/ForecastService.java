package com.github.weatherforecast.service;

import com.github.weatherforecast.exceptions.weather.BadWeatherConditionsForAllLocationsException;
import com.github.weatherforecast.model.Place;
import com.github.weatherforecast.model.Weather;
import com.github.weatherforecast.weatherAPI.WeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForecastService {

    private final DateService dateService;
    private final WeatherClient weatherClient;

    public Weather getLocationWithBestWeatherConditions(String date) {
        long amountOfDays = dateService.checkAmountOfDays(date);
        List<Weather> weatherList = getWeatherForecastForAllLocations(amountOfDays);
        List<Weather> validWeatherList = filterValidSurfingConditions(weatherList);

        return findBestWeatherLocation(validWeatherList);
    }

    public List<Weather> getWeatherForecastForAllLocations(long day) {
        List<Weather> weatherList = new ArrayList<>();

        for (Place location : Place.values()) {
            Weather weatherForLocation = weatherClient.getWeatherForCity(location.getCityName(), day);
            if (weatherForLocation != null) {
                weatherList.add(weatherForLocation);
            }
        }
        return weatherList;
    }

    public List<Weather> filterValidSurfingConditions(List<Weather> weatherList) {
        return weatherList.stream()
                .filter(this::isValidForWindsurfing)
                .toList();
    }

    private boolean isValidForWindsurfing(Weather weather) {
        return weather.getWindSpeed() >= 5 && weather.getWindSpeed() <= 18 &&
                weather.getAverageTemperature() >= 5 && weather.getAverageTemperature() <= 35;
    }

    public Weather findBestWeatherLocation(List<Weather> weatherList) {
        if (weatherList.isEmpty()) {
            throw new BadWeatherConditionsForAllLocationsException();
        }

        if (weatherList.size() == 1) {
            return weatherList.get(0);
        }

        List<Weather> updatedWeatherList = updateWeatherConditions(weatherList);
        return findBestWeather(updatedWeatherList);
    }

    private List<Weather> updateWeatherConditions(List<Weather> weatherList) {
        List<Weather> updatedWeatherList = new ArrayList<>();
        for (Weather weather : weatherList) {
            weather.setConditionsValue(calculateValueFromSelectionCriteria(weather));
            updatedWeatherList.add(weather);
        }
        return updatedWeatherList;
    }

    private Weather findBestWeather(List<Weather> weatherList) {
        return weatherList.stream()
                .max(Comparator.comparing(Weather::getConditionsValue))
                .orElseThrow(BadWeatherConditionsForAllLocationsException::new);
    }

    public double calculateValueFromSelectionCriteria(Weather weather) {
        return weather.getWindSpeed() * 3 + weather.getAverageTemperature();
    }
}
