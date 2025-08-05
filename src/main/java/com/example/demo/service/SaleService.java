package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.Sale;

import java.util.List;

public interface SaleService {
    Sale create(Sale sale);
    List<Sale> findAll();
    Sale findById(long id);
    Sale update(long id, Sale saleDetails);
    void deleteById(long id);
    void deleteAll();
}
