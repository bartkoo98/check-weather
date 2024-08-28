package com.github.weatherforecast.weatherAPI;

import com.github.weatherforecast.model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherClient {

    @Value("${weather.api.url}")
    private String apiUrl;
    @Value("${weather.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public WeatherClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
    }

    public Mono<Weather> getWeatherForCity(String city, long day) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/forecast/daily")
                        .queryParam("city", city)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(WeatherLocationDto.class)
                .map(response -> {
                    DailyWeatherDto dailyWeatherDto = response.getData()[(int) day];
                    return Weather.builder()
                            .cityName(response.getCityName())
                            .windSpeed(dailyWeatherDto.getWindSpeed())
                            .averageTemperature(dailyWeatherDto.getTemp())
                            .build();
                });
    }
}
