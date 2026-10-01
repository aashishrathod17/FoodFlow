package com.example.foodflow;

import com.example.foodflow.model.CartItem;
import com.example.foodflow.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static CartManager instance;

    private List<CartItem> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static CartManager getInstance() {

        if (instance == null) {
            instance = new CartManager();
        }

        return instance;
    }

    public void addToCart(MenuItem menuItem) {

        for (CartItem cartItem : cartItems) {

            if (cartItem.getMenuItem().getId() == menuItem.getId()) {
                cartItem.increaseQuantity();
                return;
            }
        }

        cartItems.add(new CartItem(menuItem, 1));
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public int getCartTotal() {

        int total = 0;

        for (CartItem cartItem : cartItems) {
            total += cartItem.getTotalPrice();
        }

        return total;
    }

    public int getCartItemCount() {

        int count = 0;

        for (CartItem cartItem : cartItems) {
            count += cartItem.getQuantity();
        }

        return count;
    }

    public void clearCart() {
        cartItems.clear();
    }
}