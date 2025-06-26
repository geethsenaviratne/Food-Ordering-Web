package com.my.food_ordering.repository;


import com.my.food_ordering.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%', :query, '%')) " +
            "OR lower(r.cuisineType)LIKE lower(concat('%', :query,'%'))")   //this is for search the restaurant
    List<Restaurant> findBySearchQuery(String query);
    Restaurant findByOwnerId(Long userId);


}
