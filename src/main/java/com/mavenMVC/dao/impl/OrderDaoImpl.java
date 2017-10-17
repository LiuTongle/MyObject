package com.mavenMVC.dao.impl;

import com.mavenMVC.dao.IOrderDao;
import com.mavenMVC.entity.Order;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lizai on 16/4/11.
 */

@Repository
@Transactional
public class OrderDaoImpl extends GenericDaoHibernate<Order, Long> implements IOrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return get(orderId);
    }

    @Override
    public List<Order> getOrdersByOrderIds(List<Long> orderIds, List<Integer> status, List<Integer> payStatus, Integer start, Integer offset, List<Long> receivedIds) {
        try {
            Assert.notNull(orderIds, "orderIds can not be null");
            Assert.notEmpty(orderIds, "orderIds can not be empty");
            Assert.notNull(start, "start can not be null");
            Assert.notNull(offset, "offset can not be null");
            DetachedCriteria query = DetachedCriteria.forClass(Order.class);
            Criterion criterion = Restrictions.in("orderId", orderIds);
            query.add(criterion);
            if (status != null && status.size() > 0) {
                Criterion criterion1 = Restrictions.in("orderStatus", status);
                query.add(criterion1);
            }
            if (payStatus != null && payStatus.size() > 0) {
                Criterion criterion1 = Restrictions.in("orderPayStatus", payStatus);
                query.add(criterion1);
            }
            if ((receivedIds != null) && (receivedIds.size() > 0)) {
                Criterion criterion3 = Restrictions.not(Restrictions.in("orderId", receivedIds));
                query.add(criterion3);
            }
            if (offset != null && offset > 0) {
                query.addOrder(org.hibernate.criterion.Order.desc("createTime"));
                List<Order> results = getHibernateTemplate().findByCriteria(query, start, offset);
                return results;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Order> getOrdersByDoctorId(Long dId, List<Integer> status, List<Integer> payStatus, Integer orderType, Integer start, Integer offset, List<Long> receivedIds) {
        DetachedCriteria query = DetachedCriteria.forClass(Order.class);
        if (dId != null && dId > 0) {
            Criterion criterion = Restrictions.eq("orderDid", dId);
            query.add(criterion);
        }
        if (status != null && status.size() > 0) {
            Criterion criterion = Restrictions.in("orderStatus", status);
            query.add(criterion);
        }
        if (payStatus != null && payStatus.size() > 0) {
            Criterion criterion = Restrictions.in("orderPayStatus", payStatus);
            query.add(criterion);
        }
        if (orderType != null && orderType >= 0){
            Criterion criterion = Restrictions.eq("orderType", orderType);
            query.add(criterion);
        }
        if ((receivedIds != null) && (receivedIds.size() > 0)) {
            Criterion criterion3 = Restrictions.not(Restrictions.in("orderId", receivedIds));
            query.add(criterion3);
        }
        if (offset != null && offset > 0) {
            query.addOrder(org.hibernate.criterion.Order.desc("createTime"));
            List<Order> results = getHibernateTemplate().findByCriteria(query, start, offset);
            return results;
        }
        return new ArrayList<Order>();
    }

    @Override
    public List<Order> getCommentedOrdersByDoctorId(Long dId, Integer start, Integer offset, List<Long> receivedIds) {
        DetachedCriteria query = DetachedCriteria.forClass(Order.class);
        if (dId != null && dId > 0) {
            Criterion criterion = Restrictions.eq("orderDid", dId);
            query.add(criterion);
        }
        Criterion criterion = Restrictions.isNotNull("orderComment");
        query.add(criterion);
        if ((receivedIds != null) && (receivedIds.size() > 0)) {
            Criterion criterion3 = Restrictions.not(Restrictions.in("orderId", receivedIds));
            query.add(criterion3);
        }
        if (offset != null && offset > 0) {
            query.addOrder(org.hibernate.criterion.Order.desc("lastModTime"));
            List<Order> results = getHibernateTemplate().findByCriteria(query, start, offset);
            return results;
        }
        return new ArrayList<Order>();
    }

    @Override
    public Integer getTotleOrdersMoneyByDoctorId(Long dId, List<Integer> status, List<Integer> payStatus) {
        DetachedCriteria query = DetachedCriteria.forClass(Order.class);
        if (dId != null && dId > 0) {
            Criterion criterion = Restrictions.eq("orderDid", dId);
            query.add(criterion);
        }
        if (status != null && status.size() > 0) {
            Criterion criterion = Restrictions.in("orderStatus", status);
            query.add(criterion);
        }
        if (payStatus != null && payStatus.size() > 0) {
            Criterion criterion = Restrictions.in("orderPayStatus", payStatus);
            query.add(criterion);
        }
        query.setProjection(Projections.sum("orderSettledMoney"));
        String result = getHibernateTemplate().findByCriteria(query).toString();
        if(result!=null){
            try{
                return Integer.valueOf(result);
            }catch (Exception e){
                return 0;
            }
        }else{
            return 0;
        }
    }

    @Override
    public List<Order> getOrders(List<Integer> status, List<Integer> payStatus, Integer orderType) {
        DetachedCriteria query = DetachedCriteria.forClass(Order.class);
        if (status != null && status.size() > 0) {
            Criterion criterion = Restrictions.in("orderStatus", status);
            query.add(criterion);
        }
        if (payStatus != null && payStatus.size() > 0) {
            Criterion criterion = Restrictions.in("orderPayStatus", payStatus);
            query.add(criterion);
        }
        if (orderType != null && orderType >= 0){
            Criterion criterion = Restrictions.eq("orderType", orderType);
            query.add(criterion);
        }
        query.addOrder(org.hibernate.criterion.Order.desc("createTime"));
        List<Order> results = getHibernateTemplate().findByCriteria(query);
        return results;
    }

    @Override
    public List<Order> getOrdersByDoctorIdAndTime(Long dId, List<Integer> status, List<Integer> payStatus, Long startTime, Long endTime) {
        DetachedCriteria query = DetachedCriteria.forClass(Order.class);
        List<Order> results = new ArrayList<Order>();
        if (dId != null && dId > 0) {
            Criterion criterion = Restrictions.eq("orderDid", dId);
            query.add(criterion);
        }
        if (status != null && status.size() > 0) {
            Criterion criterion = Restrictions.in("orderStatus", status);
            query.add(criterion);
        }
        if (payStatus != null && payStatus.size() > 0) {
            Criterion criterion = Restrictions.in("orderPayStatus", payStatus);
            query.add(criterion);
        }
//        if (orderType != null && orderType >= 0){
//            Criterion criterion = Restrictions.eq("orderType", orderType);
//            query.add(criterion);
//        }
        if (startTime > 0 && endTime > 0 && startTime <= endTime) {
            Criterion criterion = Restrictions.ge("orderDateTime", startTime);
            query.add(criterion);
            Criterion criterion1 = Restrictions.le("orderDateTime", endTime);
            query.add(criterion1);
        }
        query.addOrder(org.hibernate.criterion.Order.desc("createTime"));
        results = getHibernateTemplate().findByCriteria(query);
        return results;
    }

    @Override
    public List<Long> getOrderPatientIdsByDoctorId(Long dId, List<Integer> status, List<Integer> payStatus, Integer start, Integer offset, List<Long> receivedIds) {
        DetachedCriteria query = DetachedCriteria.forClass(Order.class);
        if (dId != null && dId > 0) {
            Criterion criterion = Restrictions.eq("orderDid", dId);
            query.add(criterion);
        }
        if (status != null && status.size() > 0) {
            Criterion criterion = Restrictions.in("orderStatus", status);
            query.add(criterion);
        }
        if (payStatus != null && payStatus.size() > 0) {
            Criterion criterion = Restrictions.in("orderPayStatus", payStatus);
            query.add(criterion);
        }
        if ((receivedIds != null) && (receivedIds.size() > 0)) {
            Criterion criterion3 = Restrictions.not(Restrictions.in("orderPid", receivedIds));
            query.add(criterion3);
        }
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Property.forName("orderPid"));
        query.setProjection(projectionList);
        if (offset != null && offset > 0) {
            query.addOrder(org.hibernate.criterion.Order.desc("createTime"));
            List<Long> results = getHibernateTemplate().findByCriteria(query, start, offset);
            return results;
        }
        return null;
    }

    @Override
    public List<Order> getOrdersByPatientIds(List<Long> patientIds, List<Integer> status, List<Integer> payStatus, Integer orderType, Integer start, Integer offset, List<Long> receivedIds) {
        try {
            Assert.notNull(patientIds, "patientIds can not be null");
            Assert.notEmpty(patientIds, "patientIds can not be empty");
            Assert.notNull(start, "start can not be null");
            Assert.notNull(offset, "offset can not be null");
            DetachedCriteria query = DetachedCriteria.forClass(Order.class);
            Criterion criterion = Restrictions.in("orderPid", patientIds);
            query.add(criterion);
            if (status != null && status.size() > 0) {
                Criterion criterion1 = Restrictions.in("orderStatus", status);
                query.add(criterion1);
            }
            if (payStatus != null && payStatus.size() > 0) {
                Criterion criterion1 = Restrictions.in("orderPayStatus", payStatus);
                query.add(criterion1);
            }
            if (orderType != null && orderType >= 0){
                Criterion criterion1 = Restrictions.eq("orderType", orderType);
                query.add(criterion1);
            }
            if ((receivedIds != null) && (receivedIds.size() > 0)) {
                Criterion criterion3 = Restrictions.not(Restrictions.in("orderId", receivedIds));
                query.add(criterion3);
            }
            if (offset != null && offset > 0) {
                query.addOrder(org.hibernate.criterion.Order.desc("createTime"));
                List<Order> results = getHibernateTemplate().findByCriteria(query, start, offset);
                return results;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Long> getOrderDoctorIdsByPatientIds(List<Long> patientIds, Integer start, Integer offset, List<Long> receivedIds) {
        try {
            Assert.notNull(patientIds, "patientIds can not be null");
            Assert.notNull(start, "start can not be null");
            Assert.notNull(offset, "offset can not be null");
            DetachedCriteria query = DetachedCriteria.forClass(Order.class);
            Criterion criterion = Restrictions.in("orderPid", patientIds);
            query.add(criterion);
            if ((receivedIds != null) && (receivedIds.size() > 0)) {
                Criterion criterion3 = Restrictions.not(Restrictions.in("orderDid", receivedIds));
                query.add(criterion3);
            }
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.distinct(Property.forName("orderDid")));
            query.setProjection(projectionList);
            if (offset != null && offset > 0) {
                query.addOrder(org.hibernate.criterion.Order.desc("createTime"));
                List<Long> results = getHibernateTemplate().findByCriteria(query, start, offset);
                return results;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void saveOrUpdateOrder(Order order) {
        if (order.getCreateTime() == null) {
            order.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        order.setLastModTime(Calendar.getInstance().getTimeInMillis());
        saveOrUpdate(order);
    }

    @Override
    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

}
