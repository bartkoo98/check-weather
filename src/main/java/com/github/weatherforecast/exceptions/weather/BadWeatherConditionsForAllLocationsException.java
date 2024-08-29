package com.github.weatherforecast.exceptions.weather;

public class BadWeatherConditionsForAllLocationsException extends RuntimeException{
    public BadWeatherConditionsForAllLocationsException() {
        super("Sorry, but weather conditions are bad for all searched locations!");
    }
}
