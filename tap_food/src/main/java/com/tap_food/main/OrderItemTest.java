package com.tap_food.main;

import com.tap_food.DAOImpl.OrderItemDAOImpl;
import com.tap_food.model.OrderItem;

public class OrderItemTest {

    public static void main(String[] args) {

        OrderItemDAOImpl dao = new OrderItemDAOImpl();

        OrderItem item = new OrderItem(
                0,
                1,
                1,
                2,
                398.00
        );

        dao.addOrderItem(item);

    }

}