package com.example.product.dto;

public class PriceComputeRequest {
	
	private Long productId;
    
	private Integer quantity;
	
	public PriceComputeRequest() {
		super();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
}
