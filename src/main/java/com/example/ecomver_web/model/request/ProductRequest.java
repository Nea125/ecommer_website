package com.example.ecomver_web.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private Double price;
    private String image;
    private String description;
    private Long stockQuantity;
    private Long category;
}
