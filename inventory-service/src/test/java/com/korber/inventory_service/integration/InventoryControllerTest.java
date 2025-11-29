package com.korber.inventory_service.integration;

import com.korber.inventory_service.model.ProductBatch;
import com.korber.inventory_service.model.UpdateInventoryRequest;
import com.korber.inventory_service.repository.ProductBatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductBatchRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();

        repository.save(new ProductBatch(null, "P1001", 10, LocalDate.of(2025, 5, 10)));
        repository.save(new ProductBatch(null, "P1001", 5, LocalDate.of(2025, 3, 1)));
    }

    @Test
    void testGetInventory() {
        ResponseEntity<ProductBatch[]> response =
                restTemplate.getForEntity("/inventory/P1001", ProductBatch[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
    }

    @Test
    void testUpdateInventory() {
        UpdateInventoryRequest req = new UpdateInventoryRequest();
        req.setProductId("P1001");
        req.setQuantity(3);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UpdateInventoryRequest> entity = new HttpEntity<>(req, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity("/inventory/update", entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
