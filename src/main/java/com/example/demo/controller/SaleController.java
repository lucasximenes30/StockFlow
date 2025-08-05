package com.example.demo.controller;


import com.example.demo.model.Sale;
import com.example.demo.service.impl.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    public SaleServiceImpl saleService;

    @PostMapping
    public Sale createSale(@RequestBody Sale sale){
        return saleService.create(sale);
    }

    @GetMapping("{id}")
    public Sale getSaleById(@PathVariable long id){
        return saleService.findById(id);
    }

    @GetMapping
    public List <Sale> getSale(){
        return saleService.findAll();
    }

    @PutMapping
    public Sale update(@PathVariable long id, @RequestBody Sale sale){
        return saleService.update(id, sale);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable long id){
        saleService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        saleService.deleteAll();
    }
}
