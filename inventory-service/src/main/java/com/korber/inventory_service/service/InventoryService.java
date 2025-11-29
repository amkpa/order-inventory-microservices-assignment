package com.korber.inventory_service.service;


import com.korber.inventory_service.factory.InventoryFactory;
import com.korber.inventory_service.model.ProductBatch;
import com.korber.inventory_service.repository.ProductBatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private ProductBatchRepository repository;
    @Autowired
    private InventoryFactory factory;


    public List<ProductBatch> getBatches(String productId) {
        return repository.findByProductIdOrderByExpiryDateAsc(productId);
    }
    public void updateInventory(String productId, int quantity) {
        factory.getHandler("default").updateInventory(productId, quantity);
    }
}
