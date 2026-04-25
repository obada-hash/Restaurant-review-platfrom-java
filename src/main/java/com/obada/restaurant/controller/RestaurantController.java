package com.obada.restaurant.controller;


import com.obada.restaurant.domain.dto.RestaurantCreateUpdateRequestDto;
import com.obada.restaurant.domain.dto.RestaurantDto;
import com.obada.restaurant.domain.dto.RestaurantSummaryDto;
import com.obada.restaurant.domain.entity.Restaurant;
import com.obada.restaurant.mapper.RestaurantMapper;
import com.obada.restaurant.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(
            @Valid @RequestBody RestaurantCreateUpdateRequestDto requestDto) {

        Restaurant restaurant = restaurantService.createRestaurant(
                restaurantMapper.toRestaurantCreateUpdateRequest(requestDto));
        RestaurantDto restaurantDto = restaurantMapper.toDto(restaurant);
        return new ResponseEntity<>(restaurantDto, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<RestaurantSummaryDto>> searchRestaurants(
            @RequestParam(required = false) Float minRating,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size
    ) {
        List<RestaurantSummaryDto> list = restaurantService.searchRestaurant(
                minRating, PageRequest.of(page-1, size)
        ).stream()
                .map(restaurantMapper::toSummaryDto)
                .toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
