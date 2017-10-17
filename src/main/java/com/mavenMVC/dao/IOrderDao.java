package com.mavenMVC.dao;

import com.mavenMVC.entity.Order;

import java.util.List;

/**
 * Created by lizai on 16/4/11.
 */
public interface IOrderDao {

    public Order getOrderById(Long orderId);

    public List<Order> getOrdersByOrderIds(List<Long> orderIds, List<Integer> status, List<Integer> payStatus, Integer start, Integer offset, List<Long> receivedIds);

    public List<Order> getOrdersByDoctorId(Long dId, List<Integer> status, List<Integer> payStatus, Integer orderType, Integer start, Integer end, List<Long> receivedIds);

    public List<Order> getCommentedOrdersByDoctorId(Long dId, Integer start, Integer end, List<Long> receivedIds);

    public Integer getTotleOrdersMoneyByDoctorId(Long dId, List<Integer> status, List<Integer> payStatus);

    public List<Order> getOrders(List<Integer> status, List<Integer> payStatus, Integer orderType);

    public List<Order> getOrdersByDoctorIdAndTime(Long dId, List<Integer> status, List<Integer> payStatus, Long startTime, Long endTime);

    public List<Long> getOrderPatientIdsByDoctorId(Long dId, List<Integer> status, List<Integer> payStatus, Integer start, Integer end, List<Long> receivedIds);

    public List<Order> getOrdersByPatientIds(List<Long> patientIds, List<Integer> status, List<Integer> payStatus, Integer orderType, Integer start, Integer offset, List<Long> receivedIds);

    public List<Long> getOrderDoctorIdsByPatientIds(List<Long> patientIds, Integer start, Integer offset, List<Long> receivedIds);

    public void saveOrUpdateOrder(Order order);

}
