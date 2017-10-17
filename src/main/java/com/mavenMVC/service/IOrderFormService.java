package com.mavenMVC.service;

import java.util.List;

import com.mavenMVC.entity.OrderForm;

public interface IOrderFormService {
	OrderForm getOrderFormById(Long id);

	List<OrderForm> getOrderFormsByBuyerId(Long id,int start, Integer offset, List<Long> receivedIds);

	List<OrderForm> getOrderFormsBySellerId(Long id,int start, Integer offset, List<Long> receivedIds);

	void saveOrUpdateOrderForm(OrderForm orderForm);

}
