package com.tap_food.servlet;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.tap_food.DAOImpl.UserDAOImpl;
import com.tap_food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAOImpl userDAOImpl = new UserDAOImpl();
        User user = userDAOImpl.getUser(email);

        if (user == null) {
            System.out.println("User not found: " + email);
            resp.sendRedirect("login.html");
            return;
        }

        if (BCrypt.checkpw(password, user.getPassword())) {
            resp.sendRedirect("restaurant");
        } else {
            resp.sendRedirect("login.html");
        }
    }
}
