package com.tap_food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tap_food.DAO.MenuDAO;
import com.tap_food.model.Menu;
import com.tap_food.utility.DBConnection;

public class MenuDAOImpl implements MenuDAO {

	private Connection connection;

	public MenuDAOImpl() {
		connection = DBConnection.getConnection();
	}

	@Override
	public void addMenu(Menu menu) {

		String sql = "INSERT INTO Menu(RestaurantID, ItemName, Description, Price, IsAvailable, Category, CreatedAt, UpdatedAt, DeletedAt,ImagePath) VALUES(?,?,?,?,?,?,?,?,?,?)";

		try {

			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, menu.getRestaurantId());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setDouble(4, menu.getPrice());
			pstmt.setBoolean(5, menu.isAvailable());
			pstmt.setString(6, menu.getCategory());
			pstmt.setTimestamp(7, menu.getCreatedAt());
			pstmt.setTimestamp(8, menu.getUpdatedAt());
			pstmt.setTimestamp(9, menu.getDeletedAt());
			pstmt.setString(10,menu.getImagePath());
			

			int rows = pstmt.executeUpdate();

			if (rows > 0) {
				System.out.println("Menu Added Successfully");
			} else {
				System.out.println("Menu Not Added");
			}

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Menu getMenu(int menuId) {

		String sql = "SELECT * FROM Menu WHERE MenuID=?";

		Menu menu = null;

		try {

			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, menuId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				menu = new Menu();

				menu.setMenuId(rs.getInt("MenuID"));
				menu.setRestaurantId(rs.getInt("RestaurantID"));
				menu.setItemName(rs.getString("ItemName"));
				menu.setDescription(rs.getString("Description"));
				menu.setPrice(rs.getDouble("Price"));
				menu.setAvailable(rs.getBoolean("IsAvailable"));
				menu.setRating(rs.getDouble("rating"));
				menu.setCategory(rs.getString("Category"));
				menu.setCreatedAt(rs.getTimestamp("CreatedAt"));
				menu.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
				menu.setDeletedAt(rs.getTimestamp("DeletedAt"));
				menu.setImagePath(rs.getString("ImagePath"));

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return menu;
	}

	@Override
	public void updateMenu(Menu menu) {

		String sql = "UPDATE Menu SET RestaurantID=?, ItemName=?, Description=?, Price=?, IsAvailable=?, Category=?, CreatedAt=?, UpdatedAt=?, DeletedAt=?, ImagePath=? WHERE MenuID=?";

		try {

			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, menu.getRestaurantId());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setDouble(4, menu.getPrice());
			pstmt.setBoolean(5, menu.isAvailable());

			pstmt.setString(6, menu.getCategory());
			pstmt.setTimestamp(7, menu.getCreatedAt());
			pstmt.setTimestamp(8, menu.getUpdatedAt());
			pstmt.setTimestamp(9, menu.getDeletedAt());
			pstmt.setString(10, menu.getImagePath());
			pstmt.setInt(11, menu.getMenuId());
			

			int rows = pstmt.executeUpdate();

			if (rows > 0) {
				System.out.println("Menu Updated Successfully");
			} else {
				System.out.println("Menu Not Found");
			}

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteMenu(int menuId) {

		String sql = "DELETE FROM Menu WHERE MenuID=?";

		try {

			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, menuId);

			int rows = pstmt.executeUpdate();

			if (rows > 0) {
				System.out.println("Menu Deleted Successfully");
			} else {
				System.out.println("Menu Not Found");
			}

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Menu> getAllMenus(int restaurantId) {

	    List<Menu> menuList = new ArrayList<>();

	    String sql = "SELECT * FROM Menu WHERE RestaurantID = ?";

	    try {

	        PreparedStatement pstmt = connection.prepareStatement(sql);
	        pstmt.setInt(1, restaurantId);

	        ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				Menu menu = new Menu();

				menu.setMenuId(rs.getInt("MenuID"));
				menu.setRestaurantId(rs.getInt("RestaurantID"));
				menu.setItemName(rs.getString("ItemName"));
				menu.setDescription(rs.getString("Description"));
				menu.setPrice(rs.getDouble("Price"));
				menu.setAvailable(rs.getBoolean("IsAvailable"));
				
				menu.setCategory(rs.getString("Category"));
				menu.setCreatedAt(rs.getTimestamp("CreatedAt"));
				menu.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
				menu.setDeletedAt(rs.getTimestamp("DeletedAt"));
				menu.setImagePath(rs.getString("ImagePath"));

				menuList.add(menu);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return menuList;
	}

}