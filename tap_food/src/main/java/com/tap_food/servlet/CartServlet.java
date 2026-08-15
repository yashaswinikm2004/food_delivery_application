package com.tap_food.servlet;

import java.io.IOException;

import com.tap_food.DAOImpl.MenuDAOImpl;
import com.tap_food.model.Cart;
import com.tap_food.model.CartItem;
import com.tap_food.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {

	    HttpSession session = req.getSession();

	    Cart cart = (Cart) session.getAttribute("cart");

	    String action = req.getParameter("action");

	    if ("add".equals(action)) {

	        int newRestaurantId = Integer.parseInt(req.getParameter("restaurantId"));

	        Integer restaurantId = (Integer) session.getAttribute("restaurantId");

	        if (cart == null || restaurantId == null || restaurantId != newRestaurantId) {

	            cart = new Cart();

	            session.setAttribute("cart", cart);
	            session.setAttribute("restaurantId", newRestaurantId);
	        }

	        addItemToCart(req, cart);

	    } else if ("update".equals(action)) {

	        updateItemToCart(req, cart);

	    } else if ("remove".equals(action)) {

	        removeItemToCart(req, cart);
	    }

	    RequestDispatcher rd = req.getRequestDispatcher("cart.jsp");
	    rd.forward(req, resp);
	}

	private void updateItemToCart(HttpServletRequest req, Cart cart) {

	    int menuId = Integer.parseInt(req.getParameter("menuId"));
	    int qty = Integer.parseInt(req.getParameter("qty"));

	    cart.updateItem(menuId, qty);
	}

	private void removeItemToCart(HttpServletRequest req, Cart cart) {

	    int menuId = Integer.parseInt(req.getParameter("menuId"));

	    cart.removeItem(menuId);
	}

	private void addItemToCart(HttpServletRequest req, Cart cart) {
		
		int menuId = Integer.parseInt(req.getParameter("menuId"));
		int qty = Integer.parseInt(req.getParameter("qty"));
		
		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		Menu menu=menuDAOImpl.getMenu(menuId);
		
		CartItem cartItem = new CartItem(
			    menu.getMenuId(),
			    menu.getRestaurantId(),
			    menu.getItemName(),
			    menu.getPrice(),
			    qty,
			    menu.getImagePath()
			);		
		cart.addItem(cartItem);
	}
   
}
