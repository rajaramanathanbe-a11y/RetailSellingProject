package com.example.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product.entity.PricingRule;

@Repository
public interface PricingRuleRepository
        extends JpaRepository<PricingRule, Long> {

    List<PricingRule> findByIsActiveTrueOrderByPriorityAsc();
}

