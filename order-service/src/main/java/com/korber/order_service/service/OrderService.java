package com.korber.order_service.service;


import com.korber.order_service.client.InventoryClient;
import com.korber.order_service.model.Order;
import com.korber.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private InventoryClient inventoryClient;

    public Order placeOrder(Order orderRequest) {

        // 1. Update inventory first
        inventoryClient.updateInventory(orderRequest.getProductId(), orderRequest.getQuantity());

        // 2. Save the order
        orderRequest.setOrderDate(LocalDate.now());
        return repository.save(orderRequest);
    }
}
