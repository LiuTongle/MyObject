package com.mavenMVC.service;

import com.mavenMVC.entity.Department;

import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */
public interface IDepartmentService {

    public Department getDepartmentById(Long id);

    public Department searchDepartmentByName(String name);

    public List<Department> getAllDepartments();

}
