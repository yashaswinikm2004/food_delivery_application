package com.tap_food.DAO;

import java.util.List;
import com.tap_food.model.User;

public interface UserDAO {

    int addUser(User user);

    User getUser(String email);

    void updateUser(User user);

    void deleteUser(int userId);

    List<User> getAllUsers();

	
}