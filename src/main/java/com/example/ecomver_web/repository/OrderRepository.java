package com.example.ecomver_web.repository;
import com.example.ecomver_web.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query methods if needed
}

