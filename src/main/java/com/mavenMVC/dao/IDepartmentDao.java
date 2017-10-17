package com.mavenMVC.dao;

import com.mavenMVC.entity.Department;

import java.util.List;

/**
 * Created by lizai on 16/4/11.
 */
public interface IDepartmentDao {

    public Department getDepartmentById(Long id);

    public Department getDepartmentByName(String name);

    public void saveOrUpdateDepartment(Department department);

    public List<Department> getAllDepartments();

    public List<Long> getDepartmentIdsLikeNames(String name);

}
