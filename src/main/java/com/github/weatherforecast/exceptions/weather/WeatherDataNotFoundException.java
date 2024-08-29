package com.github.weatherforecast.exceptions.weather;

public class WeatherDataNotFoundException extends RuntimeException {
    public WeatherDataNotFoundException() {
        super("No weather data received from the API.");
    }
}
