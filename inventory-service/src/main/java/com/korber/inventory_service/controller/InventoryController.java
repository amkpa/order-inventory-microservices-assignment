package com.korber.inventory_service.controller;

import com.korber.inventory_service.model.ProductBatch;
import com.korber.inventory_service.model.UpdateInventoryRequest;
import com.korber.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @GetMapping("/{productId}")
    public List<ProductBatch> getInventory(@PathVariable String productId) {
        return service.getBatches(productId);
    }

    @PostMapping("/update")
    public String updateInventory(@RequestBody UpdateInventoryRequest request) {
        service.updateInventory(request.getProductId(), request.getQuantity());
        return "Inventory updated";
    }
}
