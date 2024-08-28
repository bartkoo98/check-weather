package com.github.weatherforecast.repository;

import com.github.weatherforecast.model.Location;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationRepository {

    public List<Location> getLocations() {
        return List.of(
                new Location("Jastarnia", "Poland", 54.6963, 18.6756),
                new Location("Bridgetown","Barbados", 13.0960, -59.6145),
                new Location("Fortaleza","Brazil", -3.7172, -38.5434),
                new Location("Pissouri", "Cyprus", 34.6667, 32.7000),
                new Location("Le Morne", "Mauritius", -20.4521, 57.3121)
        );
    }
}
