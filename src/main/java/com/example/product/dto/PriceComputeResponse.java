package com.example.product.dto;

import java.math.BigDecimal;
import java.util.List;

public class PriceComputeResponse {
	
	 private BigDecimal basePrice;
	
	 private BigDecimal finalPrice;
	 
	 private Integer quantity;
	 
	 private List<AppliedRuleResponse> appliedRules;
	 
	 public PriceComputeResponse() {
		super();
	}

	 public BigDecimal getBasePrice() {
		 return basePrice;
	 }

	 public void setBasePrice(BigDecimal basePrice) {
		 this.basePrice = basePrice;
	 }

	 public BigDecimal getFinalPrice() {
		 return finalPrice;
	 }

	 public void setFinalPrice(BigDecimal finalPrice) {
		 this.finalPrice = finalPrice;
	 }

	 public Integer getQuantity() {
		 return quantity;
	 }

	 public void setQuantity(Integer quantity) {
		 this.quantity = quantity;
	 }

	 public List<AppliedRuleResponse> getAppliedRules() {
		 return appliedRules;
	 }

	 public void setAppliedRules(List<AppliedRuleResponse> appliedRules) {
		 this.appliedRules = appliedRules;
	 }
	 
	 

	 
}
