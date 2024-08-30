package com.github.weatherforecast.exceptions.date;

public class PastDateException extends RuntimeException {
    public PastDateException(String dateToCheck) {
        super("You have given a date: " + dateToCheck + " that is from the past.");
    }
}
