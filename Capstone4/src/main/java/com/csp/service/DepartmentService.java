package com.csp.service;

import com.csp.bean.Department;
import com.csp.dao.DepartmentDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public boolean saveOrUpdateDepartment(Department department) {
        departmentDao.save(department);
        return true;
    }

    public boolean deleteDepartmentById(Long departmentId) {
        departmentDao.deleteById(departmentId);
        return true;
    }

//    public List<Department> getAllDepartments() {
//        return departmentDao.findAll();
//    }
    
    public List<Department> getAllDepartments() {
        Iterator<Department> iterator = departmentDao.findAll().iterator();
        List<Department> userList = new ArrayList<>();
        while (iterator.hasNext()) {
            userList.add(iterator.next());
        }
        return userList;
    }
}