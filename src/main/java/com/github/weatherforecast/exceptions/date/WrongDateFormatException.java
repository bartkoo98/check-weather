package com.github.weatherforecast.exceptions.date;

public class WrongDateFormatException extends RuntimeException {
    public WrongDateFormatException(String dateToCheck) {
        super("You have given a date in wrong format. Yours date: " + dateToCheck);
    }
}
