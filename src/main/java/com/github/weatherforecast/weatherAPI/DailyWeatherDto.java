package com.github.weatherforecast.weatherAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
class DailyWeatherDto {

    @JsonProperty("temp")
    private double temp;

    @JsonProperty("wind_spd")
    private double windSpeed;
}
