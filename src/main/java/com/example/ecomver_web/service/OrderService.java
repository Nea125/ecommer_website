package com.example.ecomver_web.service;

import com.example.ecomver_web.model.entity.Order;
import com.example.ecomver_web.model.entity.OrderItem;
import com.example.ecomver_web.model.request.OrderItemRequest;
import com.example.ecomver_web.model.request.OrderRequest;

import java.util.List;

public interface OrderService {
    Order findOrderById(Long id);
    void createOrder(OrderRequest orderRequest);
    List<Order> findAllOrders();
}
