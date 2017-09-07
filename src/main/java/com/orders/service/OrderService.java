package com.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orders.cache.CacheableService;
import com.orders.dao.OrderDao;
import com.orders.model.Order;

@Service
public class OrderService {

	private CacheableService<Order> cacheableService;

	@Autowired
	public OrderService(CacheableService<Order> cacheableService) {
		this.cacheableService = cacheableService;
	}

	public Order get(Integer orderId) {
		return this.cacheableService.get(String.valueOf(orderId), () -> OrderDao.select(orderId));
	}

	public void create(Order order){
		this.cacheableService.set(order, o -> OrderDao.insertOrUpdate(o));
	}
	
	public void update(Order order){
		this.create(order);
	}
	
	public void delete(Order order) {
		this.cacheableService.delete(order, o -> OrderDao.delete(o));
	}

}
