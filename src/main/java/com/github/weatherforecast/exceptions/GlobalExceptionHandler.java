package com.github.weatherforecast.exceptions;

import com.github.weatherforecast.exceptions.date.PastDateException;
import com.github.weatherforecast.exceptions.date.TooDistantDateException;
import com.github.weatherforecast.exceptions.date.WrongDateFormatException;
import com.github.weatherforecast.exceptions.weather.BadWeatherConditionsForAllLocationsException;
import com.github.weatherforecast.exceptions.weather.NoMatchingLocationForWeatherException;
import com.github.weatherforecast.exceptions.weather.WeatherDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PastDateException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto pastDateExceptionHandler(PastDateException exception) {
        return new ExceptionDto(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(TooDistantDateException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto tooDistantDateExceptionHandler(TooDistantDateException exception) {
        return new ExceptionDto(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(WrongDateFormatException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto wrongDateFormatExceptionHandler(WrongDateFormatException exception) {
        return new ExceptionDto(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(WeatherDataNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto weatherDataNotFoundExceptionHandler(WeatherDataNotFoundException exception) {
        return new ExceptionDto(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(BadWeatherConditionsForAllLocationsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto badWeatherConditionsForAllLocationsExceptionHandler(BadWeatherConditionsForAllLocationsException exception) {
        return new ExceptionDto(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(NoMatchingLocationForWeatherException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto noMatchingLocationForWeatherExceptionHandler(NoMatchingLocationForWeatherException exception) {
        return new ExceptionDto(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }
}
