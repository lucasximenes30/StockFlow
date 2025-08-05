package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.model.Sale;
import com.example.demo.repository.SaleRepository;
import com.example.demo.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SaleServiceImpl implements SaleService {

    @Autowired
    public SaleRepository repository;

    @Override
    public Sale create(Sale sale) {
        return repository.save(sale);
    }

    @Override
    public List<Sale> findAll() {
        return repository.findAll();
    }

    @Override
    public Sale findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido de compra não encontrado"));
    }

    @Override
    public Sale update(long id, Sale saleDetails) {
        Sale sale = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        sale.setProductsCart(saleDetails.getProductsCart());
        sale.setNameClient(saleDetails.getNameClient());
        sale.setAddress(saleDetails.getAddress());
        sale.setEmployeeSeller(saleDetails.getEmployeeSeller());
        sale.setDateSale(saleDetails.getDateSale());

        return repository.save(sale);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);

    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
