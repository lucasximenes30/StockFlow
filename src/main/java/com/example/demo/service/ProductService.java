package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    List<Product> findAll();
    Product findById(long id);
    Product update(long id, Product productDetails);
    void delete(long id);
    void deleteAll();
}
