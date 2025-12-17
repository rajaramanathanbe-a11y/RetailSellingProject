package com.example.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductResponse create(ProductRequest request) {
    	 Product product = new Product();
         product.setName(request.getName());
         product.setBasePrice(request.getBasePrice());
         product.setCategory(request.getCategory());
         product.setBrand(request.getBrand());
         product.setStockQuantity(request.getStockQuantity());

         Product saved = repository.save(product);

         ProductResponse response = new ProductResponse();
         response.setId(saved.getId());
         response.setName(saved.getName());
         response.setBasePrice(saved.getBasePrice());
         response.setCategory(saved.getCategory());
         response.setBrand(saved.getBrand());
         response.setStockQuantity(saved.getStockQuantity());
         response.setCreatedAt(saved.getCreatedAt());
         response.setUpdatedAt(saved.getUpdatedAt());

         return response;
    }

    @Override
    public ProductResponse getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToResponse(product);
    }

    @Override
    public List<ProductResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setBasePrice(request.getBasePrice());
        product.setCategory(request.getCategory());
        product.setBrand(request.getBrand());
        product.setStockQuantity(request.getStockQuantity());

        return mapToResponse(repository.save(product));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ProductResponse mapToResponse(Product product) {
    	ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setBasePrice(product.getBasePrice());
        response.setCategory(product.getCategory());
        response.setBrand(product.getBrand());
        response.setStockQuantity(product.getStockQuantity());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        return response;
    }
}
