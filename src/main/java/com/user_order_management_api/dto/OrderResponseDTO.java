package com.user_order_management_api.dto;

public class OrderResponseDTO {

    private Integer id;
    private String productName;

    public OrderResponseDTO(Integer id, String productName) {
        this.id = id;
        this.productName = productName;
    }

    public Integer getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }
}
