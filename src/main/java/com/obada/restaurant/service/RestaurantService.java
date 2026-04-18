package com.obada.restaurant.service;


import com.obada.restaurant.domain.RestaurantCreateUpdateRequest;
import com.obada.restaurant.domain.entity.Restaurant;

public interface RestaurantService {

    Restaurant createRestaurant(RestaurantCreateUpdateRequest request);

    Restaurant getRestaurant(String id);
}
