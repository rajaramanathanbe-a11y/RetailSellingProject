package com.example.product.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.product.dto.AppliedRuleResponse;
import com.example.product.dto.PriceComputeResponse;
import com.example.product.entity.PricingRule;
import com.example.product.entity.Product;
import com.example.product.repository.PricingRuleRepository;
import com.example.product.repository.ProductRepository;

@Service
public class PriceEngineServiceImpl implements PriceEngineService {

    private final ProductRepository productRepository;
    private final PricingRuleRepository ruleRepository;

    public PriceEngineServiceImpl(ProductRepository productRepository,
                                  PricingRuleRepository ruleRepository) {
        this.productRepository = productRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public PriceComputeResponse computePrice(Long productId, Integer quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        BigDecimal basePrice = product.getBasePrice()
                .multiply(BigDecimal.valueOf(quantity));

        BigDecimal finalPrice = basePrice;

        List<AppliedRuleResponse> appliedRules = new ArrayList<>();

        List<PricingRule> rules =
                ruleRepository.findByIsActiveTrueOrderByPriorityAsc();

        for (PricingRule rule : rules) {

            if (!isRuleApplicable(rule, product, quantity)) {
                continue;
            }

            BigDecimal discount = calculateDiscount(rule, finalPrice, quantity);

            if (discount.compareTo(BigDecimal.ZERO) > 0) {
                finalPrice = finalPrice.subtract(discount);

                AppliedRuleResponse applied = new AppliedRuleResponse();
                applied.setRuleId(rule.getId());
                applied.setRuleName(rule.getName());
                applied.setRuleType(rule.getRuleType());
                applied.setDiscountAmount(discount);

                appliedRules.add(applied);
            }
        }

        PriceComputeResponse response = new PriceComputeResponse();
        response.setBasePrice(basePrice);
        response.setFinalPrice(finalPrice.max(BigDecimal.ZERO));
        response.setQuantity(quantity);
        response.setAppliedRules(appliedRules);

        return response;
    }

    // ---------------- Helper Methods ----------------

    private boolean isRuleApplicable(PricingRule rule,
                                     Product product,
                                     Integer quantity) {

        String condition = rule.getConditionJson();

        if (condition == null || condition.isEmpty()) {
            return true;
        }

        // Example simple checks (can be extended later)
        if (condition.contains("category")) {
            return condition.contains(product.getCategory());
        }

        if (condition.contains("minQuantity")) {
            int minQty = extractInt(condition);
            return quantity >= minQty;
        }

        return true;
    }

    private BigDecimal calculateDiscount(PricingRule rule,
                                         BigDecimal currentPrice,
                                         Integer quantity) {

        switch (rule.getRuleType()) {

            case PERCENTAGE_DISCOUNT:
                return currentPrice
                        .multiply(rule.getValue())
                        .divide(BigDecimal.valueOf(100));

            case FLAT_DISCOUNT:
                return rule.getValue();

            case BULK_PRICING:
                return quantity >= 10 ? rule.getValue() : BigDecimal.ZERO;

            case CATEGORY_PROMOTION:
                return currentPrice
                        .multiply(rule.getValue())
                        .divide(BigDecimal.valueOf(100));

            default:
                return BigDecimal.ZERO;
        }
    }

    private int extractInt(String json) {
        // naive example for demo
        return Integer.parseInt(json.replaceAll("\\D+", ""));
    }
}

