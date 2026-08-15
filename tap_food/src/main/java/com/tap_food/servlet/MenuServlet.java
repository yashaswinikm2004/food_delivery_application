package com.tap_food.servlet;

import java.io.IOException;

import com.tap_food.DAOImpl.MenuDAOImpl;
import java.util.List;
import com.tap_food.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int  restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		MenuDAOImpl menuDAOImpl= new MenuDAOImpl();
		
		List<Menu> allMenus=menuDAOImpl.getAllMenus(restaurantId);
		for(Menu menu:allMenus)
		{
			System.out.println(menu);
		}
		
		req.setAttribute("allMenus", allMenus);
		RequestDispatcher rd= req.getRequestDispatcher("menu.jsp");
		rd.forward(req,resp);
	}
}
