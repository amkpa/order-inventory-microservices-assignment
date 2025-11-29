package com.korber.inventory_service.model;

import lombok.Data;

@Data
public class UpdateInventoryRequest {
    private String productId;
    private Integer quantity;
}
