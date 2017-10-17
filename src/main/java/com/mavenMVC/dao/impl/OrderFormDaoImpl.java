package com.mavenMVC.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.IOrderFormDao;
import com.mavenMVC.entity.OrderForm;

@Repository
@Transactional
public class OrderFormDaoImpl extends GenericDaoHibernate<OrderForm, Long> implements IOrderFormDao {

	@Override
	public OrderForm getOrderFormById(Long id) {
		return get(id);
	}

	@Override
	public List<OrderForm> getOrderFormsByBuyerId(Long id,int start, Integer offset, List<Long> receivedIds) {
		DetachedCriteria query = DetachedCriteria.forClass(OrderForm.class);
		Criterion criterion = Restrictions.eq("buyeruserId", id);
		query.add(criterion);
		if ((receivedIds != null) && (receivedIds.size() > 0)) {
			Criterion criterion3 = Restrictions.not(Restrictions.in("orderformId", receivedIds));
			query.add(criterion3);
		}
		return getHibernateTemplate().findByCriteria(query, start, offset);
	}

	@Override
	public List<OrderForm> getOrderFormsBySellerId(Long id,int start, Integer offset, List<Long> receivedIds) {
		DetachedCriteria query = DetachedCriteria.forClass(OrderForm.class);
		Criterion criterion = Restrictions.eq("sellerUserId", id);
		query.add(criterion);
		if ((receivedIds != null) && (receivedIds.size() > 0)) {
			Criterion criterion3 = Restrictions.not(Restrictions.in("orderformId", receivedIds));
			query.add(criterion3);
		}
		return getHibernateTemplate().findByCriteria(query, start, offset);
	}

	@Override
	public void saveOrUpdateOrderForm(OrderForm orderForm) {
		if (orderForm.getCreateTime() == null) {
			orderForm.setCreateTime(Calendar.getInstance().getTimeInMillis());
		}
		orderForm.setLastmodTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(orderForm);
	}

	public OrderFormDaoImpl() {
		super(OrderForm.class);
	}

	@Override
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

}
