package com.github.weatherforecast.weatherAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
class DailyWeatherDto {

    @JsonProperty("datetime")
    //@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate dateTime;

    @JsonProperty("temp")
    private double temp;

    @JsonProperty("wind_spd")
    private double windSpeed;
}
