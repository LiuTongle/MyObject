package com.mavenMVC.dao.impl;

import com.mavenMVC.dao.ISeeCashDao;
import com.mavenMVC.entity.SeeCash;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
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
public class SeeCashDaoImpl extends GenericDaoHibernate<SeeCash,Long> implements ISeeCashDao {

    public SeeCashDaoImpl() {
        super(SeeCash.class);
    }

    @Override
    public List<SeeCash> getSeeCashByDoctorId(Long doctorId, int start, int offset, List<Long> receivedIds) {
        DetachedCriteria query = DetachedCriteria.forClass(SeeCash.class);
        List<SeeCash> results = new ArrayList<SeeCash>();
        Criterion criterion = Restrictions.eq("doctorId", doctorId);
        if(receivedIds!=null && receivedIds.size()>0){
            Criterion criterion1 = Restrictions.not(Restrictions.in("seeCashId",receivedIds));
            query.add(criterion1);
        }
        query.add(criterion);
        query.addOrder(Order.desc("createTime"));
        if(start>=0 && offset>start){
            results = getHibernateTemplate().findByCriteria(query,start,offset);
        }
        return results;
    }

    @Override
    public Integer getTotleSeeCashMoneyByDoctorId(Long doctorId) {
        DetachedCriteria query = DetachedCriteria.forClass(SeeCash.class);
        List<SeeCash> results = new ArrayList<SeeCash>();
        Criterion criterion = Restrictions.eq("doctorId", doctorId);
        query.add(criterion);
        query.setProjection(Projections.sum("seeCashAmount"));
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
    public SeeCash getSeeCashById(Long id) {
        return get(id);
    }

    @Override
    public void saveOrUpdateSeeCash(SeeCash seeCash) {
        if(seeCash.getCreateTime() == null){
            seeCash.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        seeCash.setLastModTime(Calendar.getInstance().getTimeInMillis());
        saveOrUpdate(seeCash);
    }

    @Override
    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
