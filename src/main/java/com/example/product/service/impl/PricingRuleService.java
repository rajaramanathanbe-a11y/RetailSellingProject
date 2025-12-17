package com.example.product.service.impl;

import java.util.List;

import com.example.product.dto.PricingRuleRequest;
import com.example.product.dto.PricingRuleResponse;

public interface PricingRuleService {

	PricingRuleResponse create(PricingRuleRequest request);

    PricingRuleResponse update(Long id, PricingRuleRequest request);

    PricingRuleResponse getById(Long id);

    List<PricingRuleResponse> getAll();

    void delete(Long id);
}
