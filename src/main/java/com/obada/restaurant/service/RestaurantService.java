package com.obada.restaurant.service;


import com.obada.restaurant.domain.RestaurantCreateUpdateRequest;
import com.obada.restaurant.domain.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantService {

    Restaurant createRestaurant(RestaurantCreateUpdateRequest request);

    Page<Restaurant> searchRestaurant(Float minRating, Pageable pageable);

}
