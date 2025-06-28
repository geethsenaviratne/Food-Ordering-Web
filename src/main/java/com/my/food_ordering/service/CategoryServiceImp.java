package com.my.food_ordering.service;

import com.my.food_ordering.model.Category;

import java.util.List;

public class CategoryServiceImp implements CategoryService {
    @Override
    public Category createCategory(String name, Long userId) {
        return null;
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        return List.of();
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        return null;
    }
}
