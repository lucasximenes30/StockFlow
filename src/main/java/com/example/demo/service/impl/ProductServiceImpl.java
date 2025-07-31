package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public Product update(long id, Product productDetails) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        product.setName(productDetails.getName());
        product.setCategory(productDetails.getCategory());
        product.setWeight(productDetails.getWeight());
        product.setAmount(productDetails.getAmount());
        product.setPrice(productDetails.getPrice());
        product.setPriceFinal(productDetails.getPriceFinal());

        return repository.save(product);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
