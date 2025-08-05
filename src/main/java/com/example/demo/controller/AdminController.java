package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.model.Employee;
import com.example.demo.service.impl.AdminServiceImpl;
import com.example.demo.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public AdminServiceImpl adminService;
    @Autowired
    public EmployeeServiceImpl employeeService;

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.create(admin);
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.create(employee);
    }

    @GetMapping
    public List<Admin> findAllAdmins() {
        return adminService.findAll();
    }

    @GetMapping("{id}")
    public Admin findAdminById(@PathVariable long id) {
        return adminService.findById(id);
    }

    @PutMapping("{id}")
    public Admin updateAdmin(@PathVariable long id, @RequestBody Admin adminDetails) {
         Admin admin = adminService.update(id, adminDetails);
         return adminService.create(admin);
    }

    @DeleteMapping("{id}")
    public void deleteAdmin(@PathVariable long id) {
        adminService.delete(id);
    }

    @DeleteMapping
    public void deleteAllAdmins() {
        adminService.deleteAll();
    }
}
