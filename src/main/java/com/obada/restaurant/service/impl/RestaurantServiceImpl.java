package com.obada.restaurant.service.impl;


import com.obada.restaurant.domain.GeoLocation;
import com.obada.restaurant.domain.RestaurantCreateUpdateRequest;
import com.obada.restaurant.domain.entity.Address;
import com.obada.restaurant.domain.entity.OperatingHours;
import com.obada.restaurant.domain.entity.Photo;
import com.obada.restaurant.domain.entity.Restaurant;
import com.obada.restaurant.repository.RestaurantRepo;
import com.obada.restaurant.service.GeoLocationService;
import com.obada.restaurant.service.PhotoService;
import com.obada.restaurant.service.RestaurantService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepo restaurantRepo;
    private final GeoLocationService geoLocationService;
    private final PhotoService photoService;



    @Override
    public Restaurant createRestaurant(RestaurantCreateUpdateRequest request) {

        Address address = request.getAddress();
        GeoLocation geoLocation = geoLocationService.getGeoLocate(address);
        GeoPoint geoPoint = new GeoPoint(geoLocation.getLatitude(), geoLocation.getLongitude());

        List<String> photosIds = request.getPhotos();
        List<Photo> photos = photosIds.stream()
                .map(photoUrl ->
                        Photo.builder()
                                .url(photoUrl)
                                .uploadDate(LocalDateTime.now())
                                .build())
                .toList();


        Restaurant restaurant = Restaurant.builder()
                .name(request.getName())
                .cuisineType(request.getCuisineType())
                .contactInformation(request.getContactInformation())
                .address(address)
                .geoLocation(geoPoint)
                .operatingHours(request.getOperatingHours())
                .averageRating(0f)
                .photos(photos)
                .build();

        return restaurantRepo.save(restaurant);
    }

    @Override
    public Page<Restaurant> searchRestaurant(Float minRating, Pageable pageable) {
        if(minRating != null) {
            return restaurantRepo.findByAverageRatingGreaterThanEqual(minRating, pageable);
        }

        return restaurantRepo.findAll(pageable);
    }

}
