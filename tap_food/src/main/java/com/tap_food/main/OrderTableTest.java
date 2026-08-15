package com.tap_food.main;

import java.sql.Timestamp;

import com.tap_food.DAOImpl.OrderTableDAOImpl;
import com.tap_food.model.OrderTable;

public class OrderTableTest {

    public static void main(String[] args) {

        OrderTableDAOImpl dao = new OrderTableDAOImpl();

        OrderTable order = new OrderTable(
                0,
                201,
                1,
                new Timestamp(System.currentTimeMillis()),
                450.00,
                "Preparing",
                "UPI"
        );

        dao.addOrderTable(order);

    }
}