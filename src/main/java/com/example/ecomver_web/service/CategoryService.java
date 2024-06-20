package com.example.ecomver_web.service;

import com.example.ecomver_web.model.entity.Category;
import com.example.ecomver_web.model.request.CategoryRequest;

import java.util.List;

public interface CategoryService {
    Category findCategoryById(Long id);

    List<Category> findAllCategory();

    void addNewCategory(CategoryRequest categoryRequest);

    void updateCategoryById(Long id, CategoryRequest categoryRequest);

    void deleteCategoryById(Long id);
}
