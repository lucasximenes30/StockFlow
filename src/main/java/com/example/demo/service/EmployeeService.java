package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    List<Employee> findAll();
    Employee findById(long id);
    Employee update(long id, Employee employeeDetails);
    void delete(long id);
    void deleteAll();
}
