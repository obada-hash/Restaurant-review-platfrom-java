package com.obada.restaurant.mapper;

import com.obada.restaurant.domain.RestaurantCreateUpdateRequest;
import com.obada.restaurant.domain.dto.GeoPointDto;
import com.obada.restaurant.domain.dto.RestaurantCreateUpdateRequestDto;
import com.obada.restaurant.domain.dto.RestaurantDto;
import com.obada.restaurant.domain.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    RestaurantCreateUpdateRequest toRestaurantCreateUpdateRequest(RestaurantCreateUpdateRequestDto dto);

    RestaurantDto toDto(Restaurant restaurant);

    @Mapping(target = "latitude", expression = "java(geoPoint.getLat())")
    @Mapping(target = "longitude", expression = "java(geoPoint.getLon())")
    GeoPointDto toGeoPointDto(GeoPoint geoPoint);
}
