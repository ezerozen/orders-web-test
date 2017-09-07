package com.orders.dao;

import java.util.HashMap;
import java.util.Map;

import com.orders.model.Order;

public class OrderDao {

	private static Integer ids = 1;
	private static Map<Integer, Order> repository = new HashMap<Integer, Order>();

	public static void insertOrUpdate(Order order) {
		if (order.getOrderId() == null) {
			order.setOrderId(getId());
		}
		repository.put(order.getOrderId(), order);
	}

	public static void delete(Order order) {
		repository.remove(order.getOrderId());
	}

	public static Order select(Integer orderId) {
		return repository.get(orderId);
	}

	private static Integer getId() {
		return ids++;
	}

}
