package com.example.product.service.impl;

import com.example.product.dto.PriceComputeResponse;

public interface PriceEngineService {

    PriceComputeResponse computePrice(Long productId, Integer quantity);
}

