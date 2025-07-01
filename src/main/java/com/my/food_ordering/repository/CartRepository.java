package com.my.food_ordering.repository;

import com.my.food_ordering.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart,Long> {
    public Cart findByCustomerId(Long customerId);

}
