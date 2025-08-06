package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.Product;
import com.example.demo.model.Sale;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SaleRepository;
import com.example.demo.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    public SaleRepository repository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Override
    public Sale create(Sale sale) {
        String sellerName = sale.getEmployeeSeller().getName();

        if (sellerName == null || sellerName.isBlank()) {
            throw new RuntimeException("Nome do vendedor é obrigatório.");
        }

        Employee seller = employeeRepository.findByName(sellerName)
                .orElseThrow(() -> new RuntimeException("Vendedor '" + sellerName + "' não foi encontrado."));
        sale.setEmployeeSeller(seller);

        List<Product> validatedProducts = sale.getProductsCart().stream()
                .map(this::resolveProduct)
                .toList();

        sale.setProductsCart(validatedProducts);

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
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

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

    private Product resolveProduct(Product input) {
        return productRepository.findByName(input.getName())
                .or(() -> productRepository.findById(input.getId()))
                .orElseThrow(() -> new RuntimeException(
                        "Produto \"" + input.getName() + "\" (ID: " + input.getId() + ") não existe ou está sem estoque."
                ));
    }

}
