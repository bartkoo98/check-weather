package com.github.weatherforecast.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Weather {

    private String cityName;
    private double averageTemperature;
    private double windSpeed;
    private double conditionsValue;

}
