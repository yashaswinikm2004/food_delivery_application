package com.tap_food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tap_food.DAO.UserDAO;
import com.tap_food.model.User;
import com.tap_food.utility.DBConnection;

public class UserDAOImpl implements UserDAO {

    private Connection connection;

    // Constructor
    public UserDAOImpl() {
        connection = DBConnection.getConnection();
    }

    // Add User
    @Override
    public int addUser(User user) {

        String sql = "INSERT INTO user "
                   + "(name, email, password, address, role) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getRole());

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Get User by Email
    @Override
    public User getUser(String email) {

        User user = null;

        String sql = "SELECT * FROM user WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user = new User();

                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
                user.setCreatedDate(rs.getTimestamp("createdDate"));
                user.setLastLoginDate(rs.getTimestamp("lastLoginDate"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // Update User
    @Override
    public void updateUser(User user) {

        String sql = "UPDATE user SET "
                   + "name = ?, "
                   + "email = ?, "
                   + "password = ?, "
                   + "address = ?, "
                   + "role = ? "
                   + "WHERE userId = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getRole());
            ps.setInt(6, user.getUserId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete User
    @Override
    public void deleteUser(int userId) {

        String sql = "DELETE FROM user WHERE userId = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get All Users
    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM user";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                User user = new User();

                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
                user.setCreatedDate(rs.getTimestamp("createdDate"));
                user.setLastLoginDate(rs.getTimestamp("lastLoginDate"));

                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}