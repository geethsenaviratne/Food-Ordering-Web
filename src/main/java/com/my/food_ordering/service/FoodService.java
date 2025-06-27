package com.my.food_ordering.service;

import com.my.food_ordering.model.Category;
import com.my.food_ordering.model.Food;
import com.my.food_ordering.model.Restaurant;
import com.my.food_ordering.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFoods(Long restaurantId, boolean isVegitarian,
                                         boolean isNonveg,
                                         boolean isSeaasonal,
                                         String foodCategory);

    public List<Food> searchFoods(String keyword);
    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailibility(Long foodId) throws Exception;



}
