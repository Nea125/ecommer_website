package com.example.ecomver_web.service.serviceimpl;

import com.example.ecomver_web.model.entity.Category;
import com.example.ecomver_web.model.entity.Product;
import com.example.ecomver_web.model.request.ProductRequest;
import com.example.ecomver_web.model.request.ProductRequestDTO;
import com.example.ecomver_web.repository.CategoryRepository;
import com.example.ecomver_web.repository.ProductRepository;
import com.example.ecomver_web.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public Product findProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void addNewProduct(ProductRequest productRequest) {
        Product product = mapper.map(productRequest, Product.class);
        Category category = categoryRepository.findCategoryById(productRequest.getCategory());
        product.setCategory(category);
        productRepository.save(product);
    }

    @Override
    public void updateProductById(Long productId, ProductRequest productRequest) {
        Product product = mapper.map(productRequest, Product.class);
        product.setProductId(productId);
        Category category = categoryRepository.findCategoryById(productRequest.getCategory());
        product.setCategory(category);
        productRepository.update(product);
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
}
