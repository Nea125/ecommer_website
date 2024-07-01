package com.example.ecomver_web.controller;

import com.example.ecomver_web.model.entity.Product;
import com.example.ecomver_web.model.request.ProductRequest;
import com.example.ecomver_web.model.response.APIResponse;
import com.example.ecomver_web.service.FileService;
import com.example.ecomver_web.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@SecurityRequirement(name = "bearerAuth")
public class ProductController {
    private final ProductService productService;
    private final FileService fileService;
    public ProductController(ProductService productService, FileService fileService) {
        this.productService = productService;
        this.fileService = fileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Product>> getProductById(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        APIResponse<Product> response = new APIResponse<>(
                "Product retrieved successfully",
                HttpStatus.OK,
                product,
                200,
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.findAllProducts();
        APIResponse<List<Product>> response = new APIResponse<>(
                "Get all products successfully",
                HttpStatus.OK,
                products,
                200,
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse<Void>> createProduct(
            @RequestParam("productName") String productName,
            @RequestParam("price") Double price,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image,
            @RequestParam("stockQuantity") Long stockQuantity,
            @RequestParam("category") Long category) throws IOException {
        String imageUrl = fileService.saveFile(image);
        ProductRequest productRequest = new ProductRequest(
                productName, price, description, imageUrl, stockQuantity, category);
        productService.addNewProduct(productRequest);
        APIResponse<Void> response = new APIResponse<>(
                "Product added successfully",
                HttpStatus.CREATED,
                null,
                201,
                LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        productService.updateProductById(id, productRequest);
        APIResponse<Void> response = new APIResponse<>(
                "Product updated successfully",
                HttpStatus.OK,
                null,
                200,
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        APIResponse<Void> response = new APIResponse<>(
                "Product deleted successfully",
                HttpStatus.NO_CONTENT,
                null,
                204,
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
