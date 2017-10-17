package com.mavenMVC.dao.impl;

import com.mavenMVC.dao.IScheduleDao;
import com.mavenMVC.entity.Schedule;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lizai on 15/7/23.
 */
@Repository
@Transactional
public class ScheduleDaoImpl extends GenericDaoHibernate<Schedule,Long> implements IScheduleDao {

    public ScheduleDaoImpl() {
        super(Schedule.class);
    }

    @Override
    public void saveOrUpdateSchedule(Schedule schedule) {
        if(schedule.getCreateTime() == null){
            schedule.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        schedule.setLastModTime(Calendar.getInstance().getTimeInMillis());
        saveOrUpdate(schedule);
    }

    @Override
    public List<Schedule> getSchedulesByDoctorIdAndDay(Long doctorId, String day) {
        DetachedCriteria query = DetachedCriteria.forClass(Schedule.class);
        Criterion criterion = Restrictions.eq("scheduleDid", doctorId);
        query.add(criterion);
        Criterion criterion1 = Restrictions.eq("scheduleDay", day);
        query.add(criterion1);
        query.addOrder(Order.desc("createTime"));
        List<Schedule> results = getHibernateTemplate().findByCriteria(query);
        if(results != null && results.size()>0)
            return results;
        else
            return new ArrayList<Schedule>();
    }

    @Override
    public Schedule getScheduleById(Long scheduleId) {
        return get(scheduleId);
    }

    @Override
    public void deleteSchedule(Schedule schedule) {
        getHibernateTemplate().delete(schedule);
    }

    @Override
    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

}
