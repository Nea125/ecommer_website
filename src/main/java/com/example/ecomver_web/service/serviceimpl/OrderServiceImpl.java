package com.example.ecomver_web.service.serviceimpl;

import com.example.ecomver_web.model.entity.Order;
import com.example.ecomver_web.model.entity.OrderItem;
import com.example.ecomver_web.model.entity.Product;
import com.example.ecomver_web.model.entity.User;
import com.example.ecomver_web.model.request.OrderItemRequest;
import com.example.ecomver_web.model.request.OrderRequest;
import com.example.ecomver_web.model.response.UserResponse;
import com.example.ecomver_web.repository.OrderItemRepository;
import com.example.ecomver_web.repository.OrderRepository;
import com.example.ecomver_web.repository.ProductRepository;
import com.example.ecomver_web.repository.UserRepository;
import com.example.ecomver_web.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
                            UserRepository userRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Order findOrderById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void createOrder(OrderRequest orderRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findUserByUsername(username);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        Order order = new Order();
        order.setUser(userResponse);
        order.setTotalAmount(0.0);
        Long orderId = orderRepository.createOrder(order);
        double totalAmount = 0.0;
        for (Long productId : orderRequest.getProduct()) {
            Product product = productRepository.findById(productId);
            Long newQuantity = product.getStockQuantity() - orderRequest.getQuantity();
            productRepository.updateProductStock(productId, newQuantity);
            totalAmount += orderRequest.getQuantity() * product.getPrice();
            OrderItemRequest orderItemRequest = new OrderItemRequest();
            orderItemRequest.setOrder(orderId);
            orderItemRequest.setProduct(productId);
            orderItemRequest.setQuantity(orderRequest.getQuantity());
            orderItemRepository.addOrderItem(orderItemRequest);
        }
        order.setTotalAmount(totalAmount);
    }

    @Override
    public List<Order> findAllOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findUserByUsername(username);

        return orderRepository.getAllOrders(user.getUserId());
    }
}
