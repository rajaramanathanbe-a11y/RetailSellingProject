package com.example.product.service.impl;

import java.util.List;

import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse getById(Long id);
    List<ProductResponse> getAll();
    ProductResponse update(Long id, ProductRequest request);
    void delete(Long id);
}

