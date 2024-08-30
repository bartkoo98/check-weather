package com.github.weatherforecast.service;

import com.github.weatherforecast.exceptions.date.PastDateException;
import com.github.weatherforecast.exceptions.date.TooDistantDateException;
import com.github.weatherforecast.exceptions.date.WrongDateFormatException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

@Service
public class DateService {
    private static final int MAX_DAYS_AHEAD = 6;

    public long checkAmountOfDays(String dateToCheck) {
        LocalDate today = LocalDate.now();
        LocalDate dateToCheckWeather = parseDate(dateToCheck);

        if (dateToCheckWeather.isBefore(today)) {
            throw new PastDateException(dateToCheck);
        }

        long days = ChronoUnit.DAYS.between(today, dateToCheckWeather);

        if (days > MAX_DAYS_AHEAD) {
            throw new TooDistantDateException(days);
        }

        return days;
    }

    private LocalDate parseDate(String dateToCheck) {
        try {
            return LocalDate.parse(dateToCheck);
        } catch (DateTimeParseException e) {
            throw new WrongDateFormatException(dateToCheck);
        }
    }
}
