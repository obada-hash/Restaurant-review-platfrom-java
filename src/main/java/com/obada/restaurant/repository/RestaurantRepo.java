package com.obada.restaurant.repository;

import com.obada.restaurant.domain.entity.Restaurant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends ElasticsearchRepository<Restaurant, String> {

    // add custom queries
}
