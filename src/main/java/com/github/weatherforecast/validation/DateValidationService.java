package com.github.weatherforecast.validation;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

@Service
public class DateValidationService {
    private static final int MAX_DAYS_AHEAD = 6;

    public long checkAmountOfDays(String dateToCheck) {
        LocalDate today = LocalDate.now();
        LocalDate dateToCheckWeather = parseDate(dateToCheck);

        if (dateToCheckWeather.isBefore(today)) {
            // dodac obsluge exceptionow
        }

        long days = ChronoUnit.DAYS.between(today, dateToCheckWeather);

        if (days > MAX_DAYS_AHEAD) {
            // dodac obsluge exceptionow

        }

        return days;
    }

    private LocalDate parseDate(String dateToCheck) {
        try {
            return LocalDate.parse(dateToCheck);
        } catch (DateTimeParseException e) {
            return null;
            // dodac obsluge exceptionow
        }
    }
}
