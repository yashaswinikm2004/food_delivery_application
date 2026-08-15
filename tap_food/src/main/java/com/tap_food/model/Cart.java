package com.tap_food.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Integer, CartItem> items;

    public Cart() {
        items = new HashMap<>();
    }

    public void addItem(CartItem cartItem) {

        int menuId = cartItem.getMenuId();

        if (items.containsKey(menuId)) {

            CartItem existingItem = items.get(menuId);
            existingItem.setQty(existingItem.getQty() + cartItem.getQty());

        } else {

            items.put(menuId, cartItem);
        }
    }

    public void updateItem(int menuId, int qty) {

        if (items.containsKey(menuId)) {
            items.get(menuId).setQty(qty);
        }
    }

    public void removeItem(int menuId) {

        items.remove(menuId);
    }

    public Map<Integer, CartItem> getItems() {

        return items;
    }

    public void clearCart() {

        items.clear();
    }
}