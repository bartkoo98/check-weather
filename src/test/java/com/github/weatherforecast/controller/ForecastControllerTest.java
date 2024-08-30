package com.github.weatherforecast.controller;

import com.github.weatherforecast.model.Weather;
import com.github.weatherforecast.service.ForecastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ForecastControllerTest {

    @Mock
    private ForecastService forecastService;

    @InjectMocks
    private ForecastController forecastController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(forecastController).build();
    }

    @Test
    public void getWeather_ShouldReturnOkStatusAndWeather_WhenDateIsValid() throws Exception {
        String date = "2024-09-01";
        Weather mockWeather = Weather.builder()
                .cityName("Fortaleza")
                .averageTemperature(25)
                .windSpeed(15)
                .build();

        when(forecastService.getLocationWithBestWeatherConditions(date)).thenReturn(mockWeather);

        mockMvc.perform(get("/forecast/{date}", date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cityName").value("Fortaleza"))
                .andExpect(jsonPath("$.averageTemperature").value(25.0))
                .andExpect(jsonPath("$.windSpeed").value(15.0));
    }

}
