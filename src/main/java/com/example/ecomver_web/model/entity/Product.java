package com.example.ecomver_web.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long productId;
    private String productName;
    private Double price;
    private String image;
    private String description;
    private Long stockQuantity;
    private Category category;
}

