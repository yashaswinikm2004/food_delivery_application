package com.tap_food.main;
import com.tap_food.DAOImpl.RestaurantDAOImpl;
import com.tap_food.model.Restaurant;

public class RestaurantTest {
	public static void main(String[] args) {
		
		Restaurant restaurant = new Restaurant(
		        0,
		        "KFC",
		        "Fast Food",
		        25,
		        "MG Road",
		        101,
		        4.5,
		        true,
		        "Images/kfc.png"
		);
  
		RestaurantDAOImpl dao = new RestaurantDAOImpl();
		dao.addRestaurant(restaurant);
}
	
}
