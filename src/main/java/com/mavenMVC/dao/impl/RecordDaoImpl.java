package com.mavenMVC.dao.impl;

import com.mavenMVC.dao.IRecordDao;
import com.mavenMVC.entity.Record;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created by lizai on 16/4/11.
 */

@Repository
@Transactional
public class RecordDaoImpl extends GenericDaoHibernate<Record, Long> implements IRecordDao {

    public RecordDaoImpl() {
        super(Record.class);
    }

    @Override
    public Record getRecordById(Long id) {
        return get(id);
    }

    @Override
    public List<Record> getAllRecordsByPatientId(Long patientId, int recordType) {
        DetachedCriteria query = DetachedCriteria.forClass(Record.class);
        Criterion criterion = Restrictions.eq("recordPatientId", patientId);
        query.add(criterion);
        Criterion criterion1 = Restrictions.eq("recordType", recordType);
        query.add(criterion1);
        Criterion criterion2 = Restrictions.eq("recordStatus", 0);
        query.add(criterion2);
        if (getHibernateTemplate().findByCriteria(query).size() > 0) {
            return (List<Record>) getHibernateTemplate().findByCriteria(query);
        } else {
            return null;
        }
    }

    @Override
    public Record getLastRecordByPatientId(Long patientId, int recordType) {
        DetachedCriteria query = DetachedCriteria.forClass(Record.class);
        Criterion criterion = Restrictions.eq("recordPatientId", patientId);
        query.add(criterion);
        Criterion criterion1 = Restrictions.eq("recordType", recordType);
        query.add(criterion1);
        Criterion criterion2 = Restrictions.eq("recordStatus", 0);
        query.add(criterion2);
        query.addOrder(Order.desc("createTime"));
        List<Record> records = null;
        records = getHibernateTemplate().findByCriteria(query,0,1);
        if(records!=null && records.size()>0){
            return records.get(0);
        }else{
            return null;
        }
    }

    @Override
    public void saveOrUpdateRecord(Record record) {
        if (record.getCreateTime() == null) {
            record.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        record.setLastModTime(Calendar.getInstance().getTimeInMillis());
        saveOrUpdate(record);
    }

    @Override
    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

}
