package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    public EmployeeRepository repository;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return repository.save(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Employee getEmployee(@PathVariable long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));
    }

    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody Employee employeeDetails) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        employee.setName(employeeDetails.getName());
        employee.setUsername(employeeDetails.getUsername());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPassword(employeeDetails.getPassword());
        employee.setState(employeeDetails.isState());
        employee.setRole(employeeDetails.getRole());
        employee.setDataDeContratacao(employeeDetails.getDataDeContratacao());
        return repository.save(employee);
    }


    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable long id){
        repository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllEmployee(){
        repository.deleteAll();
    }
}
