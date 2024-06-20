package com.example.ecomver_web.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String productName;
    private Long productId;
    private Double price;
    private Integer stockQuantity;
}

