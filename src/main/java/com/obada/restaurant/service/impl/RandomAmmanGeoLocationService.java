package com.obada.restaurant.service.impl;


import com.obada.restaurant.domain.GeoLocation;
import com.obada.restaurant.domain.entity.Address;
import com.obada.restaurant.service.GeoLocationService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomAmmanGeoLocationService implements GeoLocationService {

    private static final float MIN_LATITUDE = 31.85f;
    private static final float MAX_LATITUDE = 32.10f;

    private static final float MIN_LONGITUDE = 35.75f;
    private static final float MAX_LONGITUDE = 36.05f;

    @Override
    public GeoLocation getGeoLocate(Address address) {
        Random rand = new Random();
        double latitude = MIN_LATITUDE + rand.nextFloat() * MAX_LATITUDE;
        double longitude = MIN_LONGITUDE + rand.nextFloat() * MAX_LONGITUDE;

        return GeoLocation.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
