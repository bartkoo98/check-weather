package com.github.weatherforecast.service;

import com.github.weatherforecast.exceptions.date.PastDateException;
import com.github.weatherforecast.exceptions.date.TooDistantDateException;
import com.github.weatherforecast.exceptions.date.WrongDateFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DateServiceTest {

    private DateService dateService;

    @BeforeEach
    void setUp() {
        dateService = new DateService();
    }

    @Test
    void checkAmountOfDays_ShouldReturnCorrectDays_WhenDateIsValid() {
        String validDate = LocalDate.now().plusDays(3).toString();

        long result = dateService.checkAmountOfDays(validDate);

        assertEquals(3, result);
    }

    @Test
    void checkAmountOfDays_ShouldThrowPastDateException_WhenDateIsInThePast() {
        String pastDate = LocalDate.now().minusDays(1).toString();

        assertThrows(PastDateException.class, () -> dateService.checkAmountOfDays(pastDate));
    }

    @Test
    void checkAmountOfDays_ShouldThrowTooDistantDateException_WhenDateIsTooFarInFuture() {
        String tooDistantDate = LocalDate.now().plusDays(7).toString();

        assertThrows(TooDistantDateException.class, () -> dateService.checkAmountOfDays(tooDistantDate));
    }

    @Test
    void checkAmountOfDays_ShouldThrowWrongDateFormatException_WhenDateIsInWrongFormat() {
        String wrongFormatDate = "2024-08-XX";

        assertThrows(WrongDateFormatException.class, () -> dateService.checkAmountOfDays(wrongFormatDate));
    }
}
