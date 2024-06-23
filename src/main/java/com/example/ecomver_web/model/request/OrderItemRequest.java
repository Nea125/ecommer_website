package com.example.ecomver_web.model.request;

import com.example.ecomver_web.model.entity.Order;
import com.example.ecomver_web.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
    private Long order;
    private Long product;
    private Long quantity;
}
