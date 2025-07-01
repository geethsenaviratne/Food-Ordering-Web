package com.my.food_ordering.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateCartItemRequest {

    private Long cartItemId;

    private int quantity;

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
