package com.korber.order_service.client;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class InventoryClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void updateInventory(String productId, int quantity) {
        String url = "http://localhost:8081/inventory/update";

        Map<String, Object> requestBody = Map.of(
                "productId", productId,
                "quantity", quantity
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        restTemplate.postForObject(url, entity, String.class);
    }
}
