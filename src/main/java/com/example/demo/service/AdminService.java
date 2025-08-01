package com.example.demo.service;

import com.example.demo.model.Admin;
import java.util.List;

public interface AdminService {

    Admin create(Admin admin);

    List<Admin> findAll();
    Admin findById(long id);
    Admin update(long id, Admin adminDetails);
    void delete(long id);
    void deleteAll();
}
