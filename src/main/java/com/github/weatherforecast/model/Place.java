package com.github.weatherforecast.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Place {

    JASTARNIA("Jastarnia", "Poland", 54.69606, 18.67873),
    BRIDGETOWN("Bridgetown", "Barbados", 13.10732, -59.62021),
    FORTALEZA("Fortaleza", "Brazil", -3.71722, -38.54306),
    PISSOURI("Pissouri", "Cyprus", 34.66942, 32.70132),
    LE_MORNE("Le Morne", "Mauritius", 14.7, -61.0);

    private final String cityName;
    private final String country;
    private final double latitude;
    private final double longitude;

}
