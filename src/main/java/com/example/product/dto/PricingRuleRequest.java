package com.example.product.dto;

import java.math.BigDecimal;

import com.example.product.entity.RuleType;

public class PricingRuleRequest {

    private String name;
    private RuleType ruleType;
    private BigDecimal value;
    private Integer priority;
    private String conditionJson;
    private Boolean isActive;
	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RuleType getRuleType() {
		return ruleType;
	}
	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getConditionJson() {
		return conditionJson;
	}
	public void setConditionJson(String conditionJson) {
		this.conditionJson = conditionJson;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public PricingRuleRequest() {
		super();
	}

    
}

