package com.example.ecomver_web.model.request;

import com.example.ecomver_web.model.entity.OrderItem;
import com.example.ecomver_web.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<Long> product;
    private Long quantity;
}
