package com.example.ecomver_web.model.entity;

import com.example.ecomver_web.model.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long orderId;
    private UserResponse user;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private Byte isPaid;
    List<OrderItem> orderItems;
}

