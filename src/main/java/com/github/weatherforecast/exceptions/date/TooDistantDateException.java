package com.github.weatherforecast.exceptions.date;

public class TooDistantDateException extends RuntimeException {
    public TooDistantDateException(long amountOfDays) {
        super("The forecast works for a maximum of 7 days. The difference in the given date to this day is: " + amountOfDays);
    }
}
