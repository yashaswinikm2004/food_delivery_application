package com.tap_food.main;

import java.sql.Timestamp;

import com.tap_food.DAOImpl.MenuDAOImpl;
import com.tap_food.model.Menu;

public class MenuTest {

	public static void main(String[] args) {

		MenuDAOImpl dao = new MenuDAOImpl();

		Menu menu = new Menu(
		        0,
		        1,
		        "Chicken Burger",
		        "Crispy Chicken Burger",
		        199.0,
		        true,
		        4.5,                              // rating
		        "Burger",
		        new Timestamp(System.currentTimeMillis()),
		        new Timestamp(System.currentTimeMillis()),
		        null,
		        "Images/chickenBurger.png"        // imagePath
		);

		dao.addMenu(menu);

	}

}