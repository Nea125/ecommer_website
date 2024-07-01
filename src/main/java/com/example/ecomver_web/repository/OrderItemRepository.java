package com.example.ecomver_web.repository;

import com.example.ecomver_web.model.entity.OrderItem;
import com.example.ecomver_web.model.entity.Product;
import com.example.ecomver_web.model.request.OrderItemRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderItemRepository {

    @Insert("INSERT INTO tborderitem (orderId, productId, quantity) " +
            "VALUES (#{order.order}, #{order.product}, #{order.quantity})")
    void addOrderItem(@Param("order") OrderItemRequest orderItem);

    @Select("SELECT * FROM tborderitem WHERE orderId = #{orderId}")
    @Results(id = "orderItemMapping", value = {
            @Result(property = "orderItemId", column = "orderItemId"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "product", column = "productId",
                    javaType = Product.class,
                    one = @One(select = "com.example.ecomver_web.repository.ProductRepository.findById"))
    })
    List<OrderItem> findOrderItemsByOrderId(Long orderId);
}
