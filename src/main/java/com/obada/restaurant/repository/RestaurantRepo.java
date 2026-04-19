package com.obada.restaurant.repository;

import com.obada.restaurant.domain.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestaurantRepo extends ElasticsearchRepository<Restaurant, String> {

    Page<Restaurant> findByAverageRatingGreaterThanEqual(Float minRating, Pageable pageable);


}
