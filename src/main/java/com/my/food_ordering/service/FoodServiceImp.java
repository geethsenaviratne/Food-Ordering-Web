package com.my.food_ordering.service;

import com.my.food_ordering.model.Category;
import com.my.food_ordering.model.Food;
import com.my.food_ordering.model.Restaurant;
import com.my.food_ordering.repository.FoodRepository;
import com.my.food_ordering.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImp implements FoodService {

    @Autowired
    private FoodRepository foodRepository;



    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {

        Food food = new Food();

        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setPrice(req.getPrice());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setIngredients(req.getIngredients());
        food.setSeasonal(req.isSeasional());
        food.setVegetarian(req.isVegetarian());

        Food saveFood = foodRepository.save(food);
        restaurant.getFoods().add(saveFood);

        return saveFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);

    }

    @Override
    public List<Food> getRestaurantFoods(Long restaurantId,
                                         boolean isVegitarian,
                                         boolean isNonveg,
                                         boolean isSeaasonal, String foodCategory) {

        List<Food> foods = foodRepository.findAllByRestaurantId(restaurantId);

        if(isVegitarian){
            foods=filterByVegetarian(foods,isVegitarian);
        }

        if(isNonveg){
            foods=filterByNonveg(foods,isNonveg);
        }

        if(isSeaasonal){
            foods=filterBySeasonal(foods,isSeaasonal);
        }

        if(foodCategory!=null && !foodCategory.equals("")){
            foods = filterByCategory(foods,foodCategory);
        }

        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory()!=null){
               food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());

    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeaasonal) {
        return foods.stream().filter(food -> food.isSeasonal()==isSeaasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegitarian) {
        return foods.stream().filter(food -> food.isVegetarian()==isVegitarian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFoods(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> OptionalFood = foodRepository.findById(foodId);

        if(OptionalFood.isEmpty()){
            throw new Exception("FOOD NOT EXIST.........");
        }
        return OptionalFood.get();
    }

    @Override
    public Food updateAvailibility(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
