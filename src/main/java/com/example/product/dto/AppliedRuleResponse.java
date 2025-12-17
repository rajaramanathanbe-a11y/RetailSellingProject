package com.example.product.dto;

import java.math.BigDecimal;

import com.example.product.entity.RuleType;

public class AppliedRuleResponse {
	
	private Long ruleId;
	
    private String ruleName;
    
    private RuleType ruleType;
    
    private BigDecimal discountAmount;
    
	public AppliedRuleResponse() {
		super();
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public RuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
    
    

}
