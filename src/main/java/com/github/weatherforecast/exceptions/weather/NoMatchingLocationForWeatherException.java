package com.github.weatherforecast.exceptions.weather;

public class NoMatchingLocationForWeatherException extends RuntimeException{
    public NoMatchingLocationForWeatherException() {
        super("Sorry, but no matching location for weather!");
    }
}
