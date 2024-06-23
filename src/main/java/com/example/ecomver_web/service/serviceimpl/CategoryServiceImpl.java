package com.example.ecomver_web.service.serviceimpl;

import com.example.ecomver_web.model.entity.Category;
import com.example.ecomver_web.model.request.CategoryRequest;
import com.example.ecomver_web.repository.CategoryRepository;
import com.example.ecomver_web.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public List<Category> findAllCategory() {

        return categoryRepository.findAllCategory();
    }

    @Override
    public void addNewCategory(CategoryRequest categoryRequest) {
        Category category = mapper.map(categoryRequest, Category.class);
        categoryRepository.addNewCategory(category);
    }

    @Override
    public void updateCategoryById(Long id, CategoryRequest categoryRequest) {
        Category category = mapper.map(categoryRequest, Category.class);
        category.setCategoryId(id);
        categoryRepository.updateCategoryById(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteCategoryById(id);
    }
}
