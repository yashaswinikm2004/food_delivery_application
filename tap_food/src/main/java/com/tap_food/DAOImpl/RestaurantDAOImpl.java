package com.tap_food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tap_food.DAO.RestaurantDAO;
import com.tap_food.model.Restaurant;
import com.tap_food.utility.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

    private Connection connection;

    public RestaurantDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {

        String sql = "INSERT INTO Restaurant(Name, CuisineType, DeliveryTime, Address, AdminUserID, Rating, isActive, ImagePath) VALUES (?, ?, ?, ?, ?, ?,?,?)";

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getCuisineType());
            pstmt.setInt(3, restaurant.getDeliveryTime());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setInt(5, restaurant.getAdminUserId());
            pstmt.setDouble(6, restaurant.getRating());
            pstmt.setBoolean(7, restaurant.getIsActive());
            pstmt.setString(8, restaurant.getImagePath());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Restaurant Added Successfully");
            } else {
                System.out.println("Restaurant Not Added");
            }

            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurant(int restaurantId) {

        String sql = "SELECT * FROM Restaurant WHERE RestaurantID = ?";

        Restaurant restaurant = null;

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, restaurantId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                restaurant = new Restaurant();

                restaurant.setRestaurantId(rs.getInt("RestaurantID"));
                restaurant.setName(rs.getString("Name"));
                restaurant.setCuisineType(rs.getString("CuisineType"));
                restaurant.setDeliveryTime(rs.getInt("DeliveryTime"));
                restaurant.setAddress(rs.getString("Address"));
                restaurant.setAdminUserId(rs.getInt("AdminUserID"));
                restaurant.setRating(rs.getDouble("Rating"));
                restaurant.setIsActive(rs.getBoolean("isActive"));
                restaurant.setImagePath(rs.getString("ImagePath"));
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return restaurant;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {

        String sql = "UPDATE Restaurant SET Name=?, CuisineType=?, DeliveryTime=?, Address=?, AdminUserID=?, Rating=?, isActive =?, ImagePath =? WHERE RestaurantID=?";

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getCuisineType());
            pstmt.setInt(3, restaurant.getDeliveryTime());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setInt(5, restaurant.getAdminUserId());
            pstmt.setDouble(6, restaurant.getRating());
            pstmt.setInt(7, restaurant.getRestaurantId());
            pstmt.setBoolean(8, restaurant.getIsActive());
            pstmt.setString(9,  restaurant.getImagePath());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Restaurant Updated Successfully");
            } else {
                System.out.println("Restaurant Not Found");
            }

            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(int restaurantId) {

        String sql = "DELETE FROM Restaurant WHERE RestaurantID=?";

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, restaurantId);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Restaurant Deleted Successfully");
            } else {
                System.out.println("Restaurant Not Found");
            }

            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() {

        List<Restaurant> restaurantList = new ArrayList<>();

        String sql = "SELECT * FROM Restaurant";

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Restaurant restaurant = new Restaurant();

                restaurant.setRestaurantId(rs.getInt("RestaurantID"));
                restaurant.setName(rs.getString("Name"));
                restaurant.setCuisineType(rs.getString("CuisineType"));
                restaurant.setDeliveryTime(rs.getInt("DeliveryTime"));
                restaurant.setAddress(rs.getString("Address"));
                restaurant.setAdminUserId(rs.getInt("AdminUserID"));
                restaurant.setRating(rs.getDouble("Rating"));
                restaurant.setIsActive(rs.getBoolean("isActive"));
                restaurant.setImagePath(rs.getString("ImagePath"));
                

                restaurantList.add(restaurant);
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return restaurantList;
    }
}