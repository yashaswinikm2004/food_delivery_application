package com.tap_food.servlet;

import java.io.IOException;
import java.util.List;

import com.tap_food.DAOImpl.RestaurantDAOImpl;
import com.tap_food.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/restaurant")
public class RestaurantServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestaurantDAOImpl restaurantDAOImpl = new RestaurantDAOImpl();
		List<Restaurant> allRestaurants = restaurantDAOImpl.getAllRestaurants();
		
		for (Restaurant restaurant : allRestaurants) {
			System.out.println(restaurant);
		}
		req.setAttribute("allRestaurants", allRestaurants);
		RequestDispatcher rd = req.getRequestDispatcher("restaurant.jsp");
		rd.forward(req, resp);
	}
}
