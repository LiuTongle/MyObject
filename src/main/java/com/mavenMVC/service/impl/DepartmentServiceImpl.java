package com.mavenMVC.service.impl;

import com.mavenMVC.dao.IDepartmentDao;
import com.mavenMVC.entity.Department;
import com.mavenMVC.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lizai on 16/5/9.
 */

@Service("DepartmentServiceImpl")
@Transactional
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentDao iDepartmentDao;

    @Override
    public Department getDepartmentById(Long id) {
        return iDepartmentDao.getDepartmentById(id);
    }

    @Override
    public Department searchDepartmentByName(String name) {
        Department department = iDepartmentDao.getDepartmentByName(name);
        if(department==null){
            department = new Department();
            department.setDepartmentName(name);
            iDepartmentDao.saveOrUpdateDepartment(department);
        }
        return department;
    }

    @Override
    public List<Department> getAllDepartments() {
        return iDepartmentDao.getAllDepartments();
    }
}
