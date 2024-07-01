package com.example.ecomver_web.controller;

import com.example.ecomver_web.model.entity.Category;
import com.example.ecomver_web.model.request.CategoryRequest;
import com.example.ecomver_web.model.response.APIResponse;
import com.example.ecomver_web.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Category>> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findCategoryById(id);
        APIResponse<Category> response = new APIResponse<>(
                "Category retrieved successfully",
                HttpStatus.OK,
                category,
                200,
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Category>>> getAllCategories() {
        List<Category> categories = categoryService.findAllCategory();
        APIResponse<List<Category>> response = new APIResponse<>(
                "Get all categories successfully",
                HttpStatus.OK,
                categories,
                200,
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Void>> createCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.addNewCategory(categoryRequest);
        APIResponse<Void> response = new APIResponse<>(
                "Category added successfully",
                HttpStatus.CREATED,
                null,
                201,
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        categoryService.updateCategoryById(id, categoryRequest);
        APIResponse<Void> response = new APIResponse<>(
                "Category updated successfully",
                HttpStatus.OK,
                null,
                200,
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        APIResponse<Void> response = new APIResponse<>(
                "Category deleted successfully",
                HttpStatus.OK,
                null,
                200,
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
