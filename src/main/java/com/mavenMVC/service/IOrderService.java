package com.mavenMVC.service;

import com.mavenMVC.entity.Order;

import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */
public interface IOrderService {

    public Order getOrderById(Long orderId);

    public List<Order> getOrdersByOrderIds(List<Long> orderIds, List<Integer> status, List<Integer> payStatus, Integer start, Integer offset, List<Long> receivedIds);

    public List<Order> getOrdersByDoctorId(Long dId, List<Integer> status, List<Integer> payStatus, Integer orderType, Integer start, Integer end, List<Long> receivedIds);

    public List<Order> getCommentedOrdersByDoctorId(Long dId, Integer start, Integer end, List<Long> receivedIds);

    public Integer getTotleOrdersMoneyByDoctorId(Long dId, List<Integer> status, List<Integer> payStatus);

    public List<Order> getOrders(List<Integer> status, List<Integer> payStatus , Integer orderType);

    public List<Order> getOrdersByUserId(Long uid, List<Integer> status, List<Integer> payStatus, Integer orderType, Integer start, Integer offset, List<Long> receivedIds);

    public List<Order> getOrdersByDoctorId(Long dId, List<Integer> status, List<Integer> payStatus, Long startTime, Long endTime);

    public void saveOrUpdateOrder(Order order);

}
