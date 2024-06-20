package com.example.ecomver_web.repository;

import com.example.ecomver_web.model.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductRepository {

    @Select("SELECT * FROM tbProduct WHERE productId = #{productId}")
    @Results(id = "productMapping", value = {
            @Result(property = "productId", column = "productId"),
            @Result(property = "productName", column = "productName"),
            @Result(property = "image", column = "image"),
            @Result(property = "description", column = "description"),
            @Result(property = "price", column = "price"),
            @Result(property = "stockQuantity", column = "stockQTY"),
            @Result(property = "category", column = "categoryId",
                    one = @One(select = "com.example.ecomver_web.repository.CategoryRepository.findById"))
    })
    Product findById(Long productId);

    @Select("SELECT * FROM tbProduct")
    @ResultMap("productMapping")
    List<Product> findAll();

    @Insert("INSERT INTO tbProduct (productName, image, description, price, stockQTY, categoryId) " +
            "VALUES (#{productName}, #{image}, #{description}, #{price}, #{stockQuantity}, #{category.categoryId})")
    @Options(useGeneratedKeys = true, keyProperty = "productId")
    void save(Product product);

    @Update("UPDATE tbProduct SET productName = #{productName}, image = #{image}, description = #{description}, " +
            "price = #{price}, stockQTY = #{stockQuantity}, categoryId = #{category.categoryId} WHERE productId = #{productId}")
    void update(Product product);

    @Delete("DELETE FROM tbProduct WHERE productId = #{productId}")
    void deleteById(Long productId);
}
