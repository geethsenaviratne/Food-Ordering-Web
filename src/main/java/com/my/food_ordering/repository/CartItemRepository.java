package com.my.food_ordering.repository;

import com.my.food_ordering.model.Cart;
import com.my.food_ordering.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    public Cart findByCustomerId(Long userId);
}
