package com.mavenMVC.dao;

import java.util.List;

import com.mavenMVC.entity.OrderForm;

public interface IOrderFormDao {
	OrderForm getOrderFormById(Long id);

	List<OrderForm> getOrderFormsByBuyerId(Long id,int start, Integer offset, List<Long> receivedIds);

	List<OrderForm> getOrderFormsBySellerId(Long id,int start, Integer offset, List<Long> receivedIds);

	void saveOrUpdateOrderForm(OrderForm orderForm);
}
