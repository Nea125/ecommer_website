package com.example.ecomver_web.repository;

import com.example.ecomver_web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query methods if needed
}

