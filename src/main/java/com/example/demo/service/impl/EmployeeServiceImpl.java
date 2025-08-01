package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.User;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    public UserRepository userRepository;



    @Override
    public Employee create(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado."));
    }

    @Override
    public Employee update(@PathVariable long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        employee.setName(employeeDetails.getName());
        employee.setUsername(employeeDetails.getUsername());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPassword(employeeDetails.getPassword());
        employee.setRole(employeeDetails.getRole());
        employee.setSubRole(employeeDetails.getSubRole());
        employee.setDateOfHire(employeeDetails.getDateOfHire());
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(@PathVariable long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();
    }
}
