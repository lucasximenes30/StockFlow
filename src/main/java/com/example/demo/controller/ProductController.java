package com.example.demo.controller;


import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    public ProductRepository repository;

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return repository.save(product);
    }

    @GetMapping
    public List<Product> getAllProduct(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
    @PutMapping
    public Product updateProduct(@PathVariable long id, @RequestBody Product productDetails){
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        product.setName(productDetails.getName());
        product.setCategory(productDetails.getCategory());
        product.setAmount(productDetails.getAmount());
        product.setPrice(productDetails.getPrice());
        product.setPriceFinal(productDetails.getPriceFinal());

        return repository.save(product);
    }

    @DeleteMapping("/all")
    public void deleteAllProduct(){
        repository.deleteAll();
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable long id){
        repository.deleteById(id);
    }
}
