package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public User createUser(@RequestBody User user){
        return repository.save(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado."));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails){
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        user.setName(userDetails.getName());
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());

        return repository.save(user);
    }


    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id){
        repository.deleteById(id);
    }
    @DeleteMapping
    public void deleteAllUsers(){
        repository.deleteAll();
    }


}
