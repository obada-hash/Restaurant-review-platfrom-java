package com.obada.restaurant.mapper;

import com.obada.restaurant.domain.RestaurantCreateUpdateRequest;
import com.obada.restaurant.domain.dto.GeoPointDto;
import com.obada.restaurant.domain.dto.RestaurantCreateUpdateRequestDto;
import com.obada.restaurant.domain.dto.RestaurantDto;
import com.obada.restaurant.domain.dto.RestaurantSummaryDto;
import com.obada.restaurant.domain.entity.Restaurant;
import com.obada.restaurant.domain.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    RestaurantCreateUpdateRequest toRestaurantCreateUpdateRequest(RestaurantCreateUpdateRequestDto dto);

    RestaurantDto toDto(Restaurant restaurant);

    @Mapping(source = "reviews", target = "totalReviews", qualifiedByName = "populateTotalReviews")
    RestaurantSummaryDto toSummaryDto(Restaurant restaurant);

    @Named("populateTotalReviews")
    default Integer populateTotalReviews(List<Review> reviews){
        return reviews.size(); // we initialize reviews as list in entity so no need to handle null case
    }

    @Mapping(target = "latitude", expression = "java(geoPoint.getLat())")
    @Mapping(target = "longitude", expression = "java(geoPoint.getLon())")
    GeoPointDto toGeoPointDto(GeoPoint geoPoint);
}
