package com.example.ecomver_web.controller;

import com.example.ecomver_web.model.entity.Order;
import com.example.ecomver_web.model.request.OrderRequest;
import com.example.ecomver_web.model.response.APIResponse;
import com.example.ecomver_web.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@SecurityRequirement(name = "bearerAuth")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Order>> getOrderById(@PathVariable Long id) {
        Order order = orderService.findOrderById(id);
        if (order != null) {
            APIResponse<Order> response = new APIResponse<>(
                    "Order retrieved successfully",
                    HttpStatus.OK,
                    order,
                    200,
                    LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            APIResponse<Order> response = new APIResponse<>(
                    "Order not found",
                    HttpStatus.NOT_FOUND,
                    null,
                    404,
                    LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Order>>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        APIResponse<List<Order>> response = new APIResponse<>(
                "Get all orders successfully",
                HttpStatus.OK,
                orders,
                200,
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Void>> createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
        APIResponse<Void> response = new APIResponse<>(
                "Order created successfully",
                HttpStatus.CREATED,
                null,
                201,
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<APIResponse<Void>> updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
//        orderService.updateOrder(id, orderRequest);
//        APIResponse<Void> response = new APIResponse<>(
//                "Order updated successfully",
//                HttpStatus.OK,
//                null,
//                200,
//                LocalDateTime.now());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<APIResponse<Void>> deleteOrder(@PathVariable Long id) {
//        orderService.deleteOrder(id);
//        APIResponse<Void> response = new APIResponse<>(
//                "Order deleted successfully",
//                HttpStatus.NO_CONTENT,
//                null,
//                204,
//                LocalDateTime.now());
//        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
//    }
}
