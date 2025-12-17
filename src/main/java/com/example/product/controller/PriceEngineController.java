package com.example.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.dto.PriceComputeRequest;
import com.example.product.dto.PriceComputeResponse;
import com.example.product.service.impl.PriceEngineService;

@RestController
@RequestMapping("/api/price")
public class PriceEngineController {

    private final PriceEngineService service;

    public PriceEngineController(PriceEngineService service) {
        this.service = service;
    }

    @PostMapping("/compute")
    public ResponseEntity<PriceComputeResponse> compute(
            @RequestBody PriceComputeRequest request) {
 
        return ResponseEntity.ok(
                service.computePrice(
                        request.getProductId(),
                        request.getQuantity()
                )
        );
    }
}

