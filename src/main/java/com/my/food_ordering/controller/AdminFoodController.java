package com.my.food_ordering.controller;

import com.my.food_ordering.Response.MessageResponse;
import com.my.food_ordering.model.Food;
import com.my.food_ordering.model.Restaurant;
import com.my.food_ordering.model.User;
import com.my.food_ordering.request.CreateFoodRequest;
import com.my.food_ordering.service.FoodService;
import com.my.food_ordering.service.RestaurantService;
import com.my.food_ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization")String jwt ) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.findRestaurantById(req.getRestaurantId());
        Food food=foodService.createFood(req, req.getCategory(),restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization")String jwt ) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        foodService.deleteFood(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("Successfully deleted food");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
                                                      @RequestHeader("Authorization")String jwt ) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        Food food=foodService.updateAvailibility(id);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }



}
