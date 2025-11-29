package com.korber.inventory_service.factory;

public interface InventoryHandler {
    void updateInventory(String productId, int quantity);
}
