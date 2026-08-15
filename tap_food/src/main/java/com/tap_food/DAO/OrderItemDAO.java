package com.tap_food.DAO;
import java.util.List;

import com.tap_food.model.OrderItem;

public interface OrderItemDAO {
	void addOrderItem(OrderItem orderitem);
	
	OrderItem getOrderItem(int OrderItemId);
	
	void updateOrderItem(OrderItem orderitem);
	void deleteOrderItem(int OrderItemId);
	
	List<OrderItem>getAllOrderItems();
}
