package com.example.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.product.dto.PricingRuleRequest;
import com.example.product.dto.PricingRuleResponse;
import com.example.product.entity.PricingRule;
import com.example.product.repository.PricingRuleRepository;

@Service
public class PricingRuleServiceImpl implements PricingRuleService {

    private final PricingRuleRepository repository;

    public PricingRuleServiceImpl(PricingRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public PricingRuleResponse create(PricingRuleRequest request) {
        PricingRule rule = new PricingRule();
        rule.setName(request.getName());
        rule.setRuleType(request.getRuleType());
        rule.setValue(request.getValue());
        rule.setPriority(request.getPriority());
        rule.setConditionJson(request.getConditionJson());
        rule.setIsActive(
                request.getIsActive() != null ? request.getIsActive() : true
        );

        return mapToResponse(repository.save(rule));
    }

    @Override
    public PricingRuleResponse update(Long id, PricingRuleRequest request) {
        PricingRule rule = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pricing rule not found"));

        rule.setName(request.getName());
        rule.setRuleType(request.getRuleType());
        rule.setValue(request.getValue());
        rule.setPriority(request.getPriority());
        rule.setConditionJson(request.getConditionJson());
        rule.setIsActive(request.getIsActive());

        return mapToResponse(repository.save(rule));
    }

    @Override
    public PricingRuleResponse getById(Long id) {
        return mapToResponse(
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Pricing rule not found"))
        );
    }

    @Override
    public List<PricingRuleResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private PricingRuleResponse mapToResponse(PricingRule rule) {
        PricingRuleResponse response = new PricingRuleResponse();
        response.setId(rule.getId());
        response.setName(rule.getName());
        response.setRuleType(rule.getRuleType());
        response.setValue(rule.getValue());
        response.setPriority(rule.getPriority());
        response.setConditionJson(rule.getConditionJson());
        response.setIsActive(rule.getIsActive());
        response.setCreatedAt(rule.getCreatedAt());
        response.setUpdatedAt(rule.getUpdatedAt());
        return response;
    }
}
