package com.mavenMVC.dao.impl;

import com.mavenMVC.dao.IDepartmentDao;
import com.mavenMVC.entity.Department;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
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
public class DepartmentDaoImpl extends GenericDaoHibernate<Department, Long> implements IDepartmentDao {

    public DepartmentDaoImpl() {
        super(Department.class);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return get(id);
    }

    @Override
    public Department getDepartmentByName(String name) {
        DetachedCriteria query = DetachedCriteria.forClass(Department.class);
        Criterion criterion = Restrictions.eq("departmentName",name);
        query.add(criterion);
        if(getHibernateTemplate().findByCriteria(query).size()>0){
            return (Department) getHibernateTemplate().findByCriteria(query).get(0);
        }else{
            return null;
        }
    }

    @Override
    public void saveOrUpdateDepartment(Department department) {
        if(department.getCreateTime() == null){
            department.setCreateTime(Calendar.getInstance().getTimeInMillis());
        }
        department.setLastModTime(Calendar.getInstance().getTimeInMillis());
        saveOrUpdate(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        DetachedCriteria query = DetachedCriteria.forClass(Department.class);
        return getHibernateTemplate().findByCriteria(query);
    }

    @Override
    public List<Long> getDepartmentIdsLikeNames(String name) {
        DetachedCriteria query = DetachedCriteria.forClass(Department.class);
        Criterion criterion = Restrictions.like("departmentName",name, MatchMode.ANYWHERE);
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Property.forName("departmentId"));
        query.setProjection(projectionList);
        query.add(criterion);
        List<Long> results = getHibernateTemplate().findByCriteria(query);
        if(results!=null && results.size()>0){
            return results;
        }
        return null;
    }

    @Override
    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

}
