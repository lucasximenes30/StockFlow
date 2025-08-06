package com.example.demo.service.impl;

import com.example.demo.model.Admin;
import com.example.demo.model.Employee;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    public AdminRepository repository;
    @Autowired

    public EmployeeServiceImpl employeeService;

    @Override
    public Admin create(@RequestBody Admin admin) {
        return repository.save(admin);
    }

    public Employee createEmployee(Employee employee){
        return employeeService.create(employee);
    }

    @Override
    public List<Admin> findAll() {
        return repository.findAll();
    }

    @Override
    public Admin findById(@PathVariable long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin não encontrado"));
    }

    @Override
    public Admin update(@PathVariable long id, @RequestBody Admin adminDetails) {
        Admin admin = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin não encontrado"));
        admin.setName(adminDetails.getName());
        admin.setUsername(adminDetails.getUsername());
        admin.setEmail(adminDetails.getEmail());
        admin.setPassword(adminDetails.getPassword());
        admin.setRole(adminDetails.getRole());
        admin.setSubRole(adminDetails.getSubRole());
        return repository.save(admin);
    }

    @Override
    public void delete(@PathVariable long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
