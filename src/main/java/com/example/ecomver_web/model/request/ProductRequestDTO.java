package com.example.ecomver_web.model.request;

import com.example.ecomver_web.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    private String productName;
    private Double price;
    private String image;
    private String description;
    private Long stockQuantity;
    private Long category;
}
