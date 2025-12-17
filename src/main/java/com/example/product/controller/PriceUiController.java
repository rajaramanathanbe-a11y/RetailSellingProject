package com.example.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.product.dto.PriceComputeRequest;
import com.example.product.dto.PriceComputeResponse;
import com.example.product.repository.ProductRepository;
import com.example.product.service.impl.PriceEngineService;


import org.springframework.ui.Model;

@Controller
@RequestMapping("/ui")
public class PriceUiController {

    private final ProductRepository productRepository;
    private final PriceEngineService priceEngineService;

    public PriceUiController(ProductRepository productRepository,
                             PriceEngineService priceEngineService) {
        this.productRepository = productRepository;
        this.priceEngineService = priceEngineService;
    }

    // Show form
    @GetMapping("/price")
    public String showPricePage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("request", new PriceComputeRequest());
        return "price-compute";
    }

    // Compute price
    @PostMapping("/price/compute")
    public String computePrice(@ModelAttribute PriceComputeRequest request,
                               Model model) {

        PriceComputeResponse response =
                priceEngineService.computePrice(
                        request.getProductId(),
                        request.getQuantity()
                );

        model.addAttribute("response", response);
        return "result";
    }
}

