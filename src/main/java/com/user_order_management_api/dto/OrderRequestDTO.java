package com.user_order_management_api.dto;

import jakarta.validation.constraints.NotBlank;

public class OrderRequestDTO {

    @NotBlank
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
