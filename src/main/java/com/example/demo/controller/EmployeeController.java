package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    public EmployeeServiceImpl service;


    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return service.create(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public Employee getEmployee(@PathVariable long id){
        return service.findById(id);
    }

    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody Employee employeeDetails) {
        Employee employee = service.update(id, employeeDetails);
        return service.create(employee);
    }


    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable long id){
        service.delete(id);
    }

    @DeleteMapping("/all")
    public void deleteAllEmployee(){
        service.deleteAll();
    }
}
