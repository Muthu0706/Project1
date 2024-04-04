package com.csp.controller;

import com.csp.bean.Department;
import com.csp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/insertDepartments")
    public void performInsert(@RequestBody Department department) {
        departmentService.saveOrUpdateDepartment(department);
    }

    @PutMapping("/updateDepartments")
    public void performUpdate(@RequestBody Department department) {
        departmentService.saveOrUpdateDepartment(department);
    }

    @DeleteMapping("/deleteDepartments/{departmentId}")
    public void performDelete(@PathVariable Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
    }

    @GetMapping("/findAllDepartments")
    public List<Department> viewAllDepartments() {
        return departmentService.getAllDepartments();
    }
}