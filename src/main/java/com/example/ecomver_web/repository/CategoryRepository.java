package com.example.ecomver_web.repository;

import com.example.ecomver_web.model.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryRepository {

    @Select("SELECT * FROM tbCategory WHERE categoryId = #{categoryId}")
    Category findCategoryById(Long categoryId);

    @Select("SELECT * FROM tbCategory")
    List<Category> findAllCategory();

    @Insert("INSERT INTO tbCategory (categoryName) VALUES (#{categoryName})")
    void addNewCategory(Category category);

    @Update("UPDATE tbCategory SET categoryName = #{categoryName} WHERE categoryId = #{categoryId}")
    void updateCategoryById(Category category);

    @Delete("DELETE FROM tbCategory WHERE categoryId = #{categoryId}")
    void deleteCategoryById(Long categoryId);
}
