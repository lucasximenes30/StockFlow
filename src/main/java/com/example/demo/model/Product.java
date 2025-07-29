package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long   id;

    private String name;
    private String category;
    private int    amount;
    private double price;
    private double priceFinal;

    @JsonProperty("profit")
    public BigDecimal getPofit(){
        return BigDecimal.valueOf((priceFinal - price) * amount)
                .setScale(2, RoundingMode.HALF_UP);
    }
}