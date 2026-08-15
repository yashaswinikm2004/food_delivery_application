package com.tap_food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tap_food.DAO.OrderItemDAO;
import com.tap_food.model.OrderItem;
import com.tap_food.utility.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    private Connection connection;

    public OrderItemDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {

        String sql = "INSERT INTO orderitem(OrderID, MenuID, Quantity, ItemTotal) VALUES(?,?,?,?)";

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setDouble(4, orderItem.getItemTotal());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Order Item Added Successfully");
            } else {
                System.out.println("Order Item Not Added");
            }

            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {

        String sql = "SELECT * FROM orderitem WHERE OrderItemID=?";

        OrderItem orderItem = null;

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, orderItemId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                orderItem = new OrderItem();

                orderItem.setOrderItemId(rs.getInt("OrderItemID"));
                orderItem.setOrderId(rs.getInt("OrderID"));
                orderItem.setMenuId(rs.getInt("MenuID"));
                orderItem.setQuantity(rs.getInt("Quantity"));
                orderItem.setItemTotal(rs.getDouble("ItemTotal"));

            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderItem;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {

        String sql = "UPDATE orderitem SET OrderID=?, MenuID=?, Quantity=?, ItemTotal=? WHERE OrderItemID=?";

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setDouble(4, orderItem.getItemTotal());
            pstmt.setInt(5, orderItem.getOrderItemId());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Order Item Updated Successfully");
            } else {
                System.out.println("Order Item Not Found");
            }

            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteOrderItem(int orderItemId) {

        String sql = "DELETE FROM orderitem WHERE OrderItemID=?";

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, orderItemId);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Order Item Deleted Successfully");
            } else {
                System.out.println("Order Item Not Found");
            }

            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OrderItem> getAllOrderItems() {

        List<OrderItem> orderItemList = new ArrayList<>();

        String sql = "SELECT * FROM orderitem";

        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                OrderItem orderItem = new OrderItem();

                orderItem.setOrderItemId(rs.getInt("OrderItemID"));
                orderItem.setOrderId(rs.getInt("OrderID"));
                orderItem.setMenuId(rs.getInt("MenuID"));
                orderItem.setQuantity(rs.getInt("Quantity"));
                orderItem.setItemTotal(rs.getDouble("ItemTotal"));

                orderItemList.add(orderItem);

            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderItemList;
    }

}