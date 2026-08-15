package com.tap_food.DAO;

import java.util.List;
import com.tap_food.model.OrderTable;

public interface OrderTableDAO {
	void addOrderTable(OrderTable ordertable);
	OrderTable getOrderTable(int OrderId);
	
	void updateOrderTable(OrderTable ordertable);
	void deleteOrderTable(int orderId);
	
	List<OrderTable>getAllOrdersTable();
}
