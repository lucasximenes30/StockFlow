package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
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
    public AdminRepository repository;

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return repository.save(admin);
    }

    @GetMapping
    public List<Admin> findAllAdmins() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Admin findAdminById(@PathVariable long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin não encontrado"));
    }

    @PutMapping("{id}")
    public Admin updateAdmin(@PathVariable long id, @RequestBody Admin adminDetails) {
        Admin admin = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin não encontrado"));
        admin.setName(adminDetails.getName());
        admin.setUsername(adminDetails.getUsername());
        admin.setEmail(adminDetails.getEmail());
        admin.setPassword(adminDetails.getPassword());
        admin.setState(adminDetails.isState());
        admin.setRole(adminDetails.getRole());
        admin.setSubRole(adminDetails.getSubRole());
        return repository.save(admin);
    }

    @DeleteMapping("{id}")
    public void deleteAdmin(@PathVariable long id) {
        repository.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllAdmins() {
        repository.deleteAll();
    }
}
