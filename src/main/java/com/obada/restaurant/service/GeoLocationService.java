package com.obada.restaurant.service;


import com.obada.restaurant.domain.GeoLocation;
import com.obada.restaurant.domain.entity.Address;

public interface GeoLocationService {

    GeoLocation getGeoLocate(Address address);
}
