package com.github.weatherforecast.weatherAPI;

import com.github.weatherforecast.exceptions.weather.WeatherDataNotFoundException;
import com.github.weatherforecast.model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherClient {

    @Value("${weather.api.url}")
    private String apiUrl;
    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();


    public Weather getWeatherForCity(String city, long day) {
        String url = String.format("%s?city=%s&key=%s", apiUrl, city, apiKey);

        WeatherLocationDto response = restTemplate.getForObject(url, WeatherLocationDto.class);
        if (response != null) {
            return Weather.builder()
                    .cityName(response.getCityName())
                    .windSpeed(response.getData()[(int) day].getWindSpeed())
                    .averageTemperature(response.getData()[(int) day].getTemp())
                    .build();
        } else {
            throw new WeatherDataNotFoundException();
        }
    }
}
