package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return service.create(product);
    }

    @GetMapping
    public List<Product> getAllProduct(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable long id){
        return service.findById(id);
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product productDetails){
        return service.update(id, productDetails);
    }

    @DeleteMapping("/all")
    public void deleteAllProduct(){
        service.deleteAll();
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable long id){
        service.delete(id);
    }
}
