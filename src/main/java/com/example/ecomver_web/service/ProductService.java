package com.example.ecomver_web.service;

import com.example.ecomver_web.model.entity.Product;
import com.example.ecomver_web.model.request.ProductRequest;
import com.example.ecomver_web.model.request.ProductRequestDTO;

import java.util.List;

public interface ProductService {
    Product findProductById(Long productId);
    List<Product> findAllProducts();
    void addNewProduct(ProductRequest productRequest);
    void updateProductById(Long productId, ProductRequest productRequest);
    void deleteProductById(Long productId);
}
