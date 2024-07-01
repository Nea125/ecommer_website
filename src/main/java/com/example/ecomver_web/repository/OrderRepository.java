package com.example.ecomver_web.repository;

import com.example.ecomver_web.model.entity.Order;
import com.example.ecomver_web.model.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderRepository {

    @Select("SELECT * FROM tbOrder WHERE orderId = #{id}")
    @Results(id = "orderMapping", value = {
            @Result(property = "orderId", column = "orderId"),
            @Result(property = "user", column = "userId", javaType = User.class,
                    one = @One(select = "com.example.ecomver_web.repository.UserRepository.findUserById")),
            @Result(property = "orderDate", column = "orderDate"),
            @Result(property = "totalAmount", column = "totalAmount"),
            @Result(property = "isPaid", column = "isPaid"),
            @Result(property = "orderItems", column = "orderId",
                    javaType = List.class,
                    many = @Many(select = "com.example.ecomver_web.repository.OrderItemRepository.findOrderItemsByOrderId"))
    })
    Order findOrderById(Long id);

    @Insert("INSERT INTO tbOrder (userId, totalAmount, isPaid) " +
            "VALUES (#{user.userId}, #{totalAmount}, #{isPaid})")
    Long createOrder(Order order);

    @Select("SELECT * FROM tbOrder WHERE userId = #{userId}")
    @ResultMap("orderMapping")
    List<Order> getAllOrders(Long userId);
}
