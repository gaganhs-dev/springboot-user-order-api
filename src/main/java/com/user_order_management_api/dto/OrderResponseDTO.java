package com.user_order_management_api.dto;

import java.io.Serializable;

public class OrderResponseDTO implements Serializable {

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
