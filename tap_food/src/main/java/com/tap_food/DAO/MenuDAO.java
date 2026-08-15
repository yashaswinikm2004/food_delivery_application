package com.tap_food.DAO;
import com.tap_food.model.Menu;

import java.util.List;

public interface MenuDAO {
	void addMenu(Menu menu);
	
	Menu getMenu(int MenuId);
	
	void updateMenu(Menu menu);
	void deleteMenu(int menu);
	
	
	List<Menu> getAllMenus(int restaurantId);
	
}
