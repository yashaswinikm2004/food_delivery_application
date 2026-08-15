package com.tap_food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tap_food.DAO.OrderTableDAO;
import com.tap_food.model.OrderTable;
import com.tap_food.utility.DBConnection;

public class OrderTableDAOImpl implements OrderTableDAO {

    private Connection connection;

    public OrderTableDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void addOrderTable(OrderTable orderTable) {

        String sql = "INSERT INTO ordertable(UserID, RestaurantID, OrderDate, TotalAmount, Status, PaymentMethod) VALUES(?,?,?,?,?,?)";

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, orderTable.getUserId());
            pstmt.setInt(2, orderTable.getRestaurantId());
            pstmt.setTimestamp(3, orderTable.getOrderDate());
            pstmt.setDouble(4, orderTable.getTotalAmount());
            pstmt.setString(5, orderTable.getStatus());
            pstmt.setString(6, orderTable.getPaymentMethod());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Order Added Successfully");
            } else {
                System.out.println("Order Not Added");
            }

            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public OrderTable getOrderTable(int orderId) {

        String sql = "SELECT * FROM ordertable WHERE OrderID=?";

        OrderTable orderTable = null;

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, orderId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                orderTable = new OrderTable();

                orderTable.setOrderId(rs.getInt("OrderID"));
                orderTable.setUserId(rs.getInt("UserID"));
                orderTable.setRestaurantId(rs.getInt("RestaurantID"));
                orderTable.setOrderDate(rs.getTimestamp("OrderDate"));
                orderTable.setTotalAmount(rs.getDouble("TotalAmount"));
                orderTable.setStatus(rs.getString("Status"));
                orderTable.setPaymentMethod(rs.getString("PaymentMethod"));

            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderTable;
    }

    @Override
    public void updateOrderTable(OrderTable orderTable) {

        String sql = "UPDATE ordertable SET UserID=?, RestaurantID=?, OrderDate=?, TotalAmount=?, Status=?, PaymentMethod=? WHERE OrderID=?";

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, orderTable.getUserId());
            pstmt.setInt(2, orderTable.getRestaurantId());
            pstmt.setTimestamp(3, orderTable.getOrderDate());
            pstmt.setDouble(4, orderTable.getTotalAmount());
            pstmt.setString(5, orderTable.getStatus());
            pstmt.setString(6, orderTable.getPaymentMethod());
            pstmt.setInt(7, orderTable.getOrderId());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Order Updated Successfully");
            } else {
                System.out.println("Order Not Found");
            }

            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteOrderTable(int orderId) {

        String sql = "DELETE FROM ordertable WHERE OrderID=?";

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, orderId);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Order Deleted Successfully");
            } else {
                System.out.println("Order Not Found");
            }

            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OrderTable> getAllOrdersTable() {

        List<OrderTable> orderList = new ArrayList<>();

        String sql = "SELECT * FROM ordertable";

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                OrderTable orderTable = new OrderTable();

                orderTable.setOrderId(rs.getInt("OrderID"));
                orderTable.setUserId(rs.getInt("UserID"));
                orderTable.setRestaurantId(rs.getInt("RestaurantID"));
                orderTable.setOrderDate(rs.getTimestamp("OrderDate"));
                orderTable.setTotalAmount(rs.getDouble("TotalAmount"));
                orderTable.setStatus(rs.getString("Status"));
                orderTable.setPaymentMethod(rs.getString("PaymentMethod"));

                orderList.add(orderTable);

            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderList;
    }
}