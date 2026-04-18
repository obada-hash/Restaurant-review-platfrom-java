package com.obada.restaurant.controller;


import com.obada.restaurant.domain.dto.RestaurantCreateUpdateRequestDto;
import com.obada.restaurant.domain.dto.RestaurantDto;
import com.obada.restaurant.domain.entity.Restaurant;
import com.obada.restaurant.mapper.RestaurantMapper;
import com.obada.restaurant.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
