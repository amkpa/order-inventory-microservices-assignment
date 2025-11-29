package com.korber.inventory_service.factory;


import com.korber.inventory_service.model.ProductBatch;
import com.korber.inventory_service.repository.ProductBatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("defaultInventoryHandler")
public class DefaultInventoryHandler implements InventoryHandler {

    @Autowired
    private ProductBatchRepository repository;

    @Override
    public void updateInventory(String productId, int quantity) {

        List<ProductBatch> batches =
                repository.findByProductIdOrderByExpiryDateAsc(productId);

        int remaining = quantity;

        for (ProductBatch batch : batches) {
            if (remaining <= 0) break;

            int deduct = Math.min(batch.getQuantity(), remaining);
            batch.setQuantity(batch.getQuantity() - deduct);
            remaining -= deduct;

            repository.save(batch);
        }

        if (remaining > 0) {
            throw new RuntimeException("Not enough stock available");
        }
    }
}
