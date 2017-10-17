package com.mavenMVC.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.IOrderFormDao;
import com.mavenMVC.entity.OrderForm;
import com.mavenMVC.service.IOrderFormService;

@Service("OrderFormServiceImpl")
@Transactional
public class OrderFormServiceImpl implements IOrderFormService {

	@Autowired
	private IOrderFormDao orderFormDao;

	@Override
	public OrderForm getOrderFormById(Long id) {
		return orderFormDao.getOrderFormById(id);
	}

	@Override
	public List<OrderForm> getOrderFormsByBuyerId(Long id,int start, Integer offset, List<Long> receivedIds) {
		return orderFormDao.getOrderFormsByBuyerId(id,0, offset, receivedIds);
	}

	@Override
	public List<OrderForm> getOrderFormsBySellerId(Long id,int start, Integer offset, List<Long> receivedIds) {
		return orderFormDao.getOrderFormsBySellerId(id,0, offset, receivedIds);
	}

	@Override
	public void saveOrUpdateOrderForm(OrderForm orderForm) {
		orderFormDao.saveOrUpdateOrderForm(orderForm);
	}

}
