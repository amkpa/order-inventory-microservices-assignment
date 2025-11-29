package com.korber.order_service.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void updateInventory(String productId, int quantity) {

        String url = "http://localhost:8081/inventory/update";

        Map<String, Object> request = new HashMap<>();
        request.put("productId", productId);
        request.put("quantity", quantity);

        restTemplate.postForObject(url, request, String.class);
    }
}
