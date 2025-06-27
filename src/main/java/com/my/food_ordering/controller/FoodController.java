package com.my.food_ordering.controller;


import com.my.food_ordering.model.Food;
import com.my.food_ordering.model.Restaurant;
import com.my.food_ordering.model.User;
import com.my.food_ordering.repository.UserRepository;
import com.my.food_ordering.request.CreateFoodRequest;
import com.my.food_ordering.service.FoodService;
import com.my.food_ordering.service.RestaurantService;
import com.my.food_ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization")String jwt ) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        List<Food> food=foodService.searchFoods(name);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam boolean vegetarian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonveg,
            @RequestParam (required = false) String food_category,
            @PathVariable long restaurantId,
            @RequestHeader("Authorization")String jwt ) throws Exception {

        User user=userService.findUserByJwtToken(jwt);

        List<Food> food=foodService.getRestaurantFoods(restaurantId,vegetarian,seasonal,nonveg,food_category);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }



}
