package com.github.weatherforecast.service;

import com.github.weatherforecast.exceptions.weather.BadWeatherConditionsForAllLocationsException;
import com.github.weatherforecast.model.Weather;
import com.github.weatherforecast.weatherAPI.WeatherClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ForecastServiceTest {

    @Mock
    private DateService dateService;

    @Mock
    private WeatherClient weatherClient;

    @InjectMocks
    private ForecastService forecastService;

    private List<Weather> mockWeatherList;

    @BeforeEach
    public void setup() {
        mockWeatherList = new ArrayList<>();
        mockWeatherList.add(Weather.builder().cityName("Jastarnia").averageTemperature(10).windSpeed(8).build());
        mockWeatherList.add(Weather.builder().cityName("Bridgetown").averageTemperature(28).windSpeed(12).build());
        mockWeatherList.add(Weather.builder().cityName("Fortaleza").averageTemperature(25).windSpeed(15).build());
    }

    @Test
    public void getLocationWithBestWeatherConditions_ShouldReturnBestLocation_WhenDateIsValid() {
        String date = "2024-09-01";
        long days = 3;

        when(dateService.checkAmountOfDays(date)).thenReturn(days);
        when(weatherClient.getWeatherForCity("Jastarnia", days)).thenReturn(mockWeatherList.get(0));
        when(weatherClient.getWeatherForCity("Bridgetown", days)).thenReturn(mockWeatherList.get(1));
        when(weatherClient.getWeatherForCity("Fortaleza", days)).thenReturn(mockWeatherList.get(2));

        Weather bestWeather = forecastService.getLocationWithBestWeatherConditions(date);

        assertEquals("Fortaleza", bestWeather.getCityName());
        assertEquals(25, bestWeather.getAverageTemperature());
        assertEquals(15, bestWeather.getWindSpeed());
    }

    @Test
    public void getLocationWithBestWeatherConditions_ShouldThrowException_WhenAllLocationsHaveBadWeather() {
        String date = "2024-09-01";
        long days = 3;

        when(dateService.checkAmountOfDays(date)).thenReturn(days);
        when(weatherClient.getWeatherForCity(anyString(), eq(days))).thenReturn(null);

        assertThrows(BadWeatherConditionsForAllLocationsException.class, () -> {
            forecastService.getLocationWithBestWeatherConditions(date);
        });
    }

    @Test
    public void getWeatherForecastForAllLocations_ShouldReturnWeatherList_WhenWeatherDataIsAvailable() {
        long days = 3;

        when(weatherClient.getWeatherForCity("Jastarnia", days)).thenReturn(mockWeatherList.get(0));
        when(weatherClient.getWeatherForCity("Bridgetown", days)).thenReturn(mockWeatherList.get(1));
        when(weatherClient.getWeatherForCity("Fortaleza", days)).thenReturn(mockWeatherList.get(2));

        List<Weather> weatherList = forecastService.getWeatherForecastForAllLocations(days);

        assertEquals(3, weatherList.size());
    }

    @Test
    public void filterValidSurfingConditions_ShouldReturnFilteredWeatherList_WhenConditionsAreValid() {
        mockWeatherList.add(Weather.builder().cityName("Le Morne").averageTemperature(2).windSpeed(2).build());

        List<Weather> validWeatherList = forecastService.filterValidSurfingConditions(mockWeatherList);

        assertEquals(3, validWeatherList.size());
        assertTrue(validWeatherList.stream().noneMatch(weather -> weather.getCityName().equals("Le Morne")));
    }

    @Test
    public void findBestWeatherLocation_ShouldReturnSingleWeather_WhenOnlyOneValidConditionExists() {
        List<Weather> singleValidWeather = List.of(mockWeatherList.get(0));

        Weather bestWeather = forecastService.findBestWeatherLocation(singleValidWeather);

        assertEquals("Jastarnia", bestWeather.getCityName());
    }

    @Test
    public void findBestWeatherLocation_ShouldReturnBestWeather_WhenMultipleValidConditionsExist() {
        Weather bestWeather = forecastService.findBestWeatherLocation(mockWeatherList);

        assertEquals("Fortaleza", bestWeather.getCityName());
    }

    @Test
    public void calculateValueFromSelectionCriteria_ShouldReturnCorrectValue_WhenWeatherDataIsValid() {
        Weather weather = Weather.builder().cityName("TestCity").windSpeed(10).averageTemperature(20).build();

        double value = forecastService.calculateValueFromSelectionCriteria(weather);

        assertEquals(50, value);
    }
}
