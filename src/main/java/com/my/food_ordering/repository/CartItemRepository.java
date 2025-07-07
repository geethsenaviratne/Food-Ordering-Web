package com.my.food_ordering.repository;

import com.my.food_ordering.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    List<CartItem> findByCartCustomerId(Long customerId);

}
