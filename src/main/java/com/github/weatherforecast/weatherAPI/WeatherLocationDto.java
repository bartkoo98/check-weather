package com.github.weatherforecast.weatherAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
class WeatherLocationDto {
    @JsonProperty("city_name")
    private String cityName;

    @JsonProperty("lat")
    private double lat;

    @JsonProperty("lon")
    private double lon;

    @JsonProperty("data")
    private DailyWeatherDto[] data;
}
