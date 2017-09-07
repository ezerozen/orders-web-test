package com.orders.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.orders.model.Order;
import com.orders.service.OrderService;

@Controller
@RequestMapping("orders")
public class OrderController {

	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResponseEntity<Order> save(@RequestBody Order order) {
		this.orderService.create(order);
		return new ResponseEntity<Order>(HttpStatus.CREATED);
	}
	
	@ResponseBody
	@RequestMapping(value = "{orderId}", method = RequestMethod.GET)
	public ResponseEntity<Order> get(@PathVariable("orderId") Integer orderId){
		Order order = this.orderService.get(orderId);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
}
