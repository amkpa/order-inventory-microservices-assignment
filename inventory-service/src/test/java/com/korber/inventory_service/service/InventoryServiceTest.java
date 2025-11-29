package com.korber.inventory_service.service;

import com.korber.inventory_service.factory.InventoryFactory;
import com.korber.inventory_service.factory.InventoryHandler;
import com.korber.inventory_service.model.ProductBatch;
import com.korber.inventory_service.repository.ProductBatchRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    @Mock
    private ProductBatchRepository repository;

    @Mock
    private InventoryFactory factory;

    @Mock
    private InventoryHandler handler;

    @InjectMocks
    private InventoryService service;

    public InventoryServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBatches() {
        List<ProductBatch> mockList = List.of(
                new ProductBatch()
        );

        when(repository.findByProductIdOrderByExpiryDateAsc("P1001"))
                .thenReturn(mockList);

        List<ProductBatch> result = service.getBatches("P1001");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findByProductIdOrderByExpiryDateAsc("P1001");
    }

    @Test
    void testUpdateInventoryDelegatesToHandler() {
        when(factory.getHandler("default")).thenReturn(handler);

        service.updateInventory("P1001", 5);

        verify(handler, times(1)).updateInventory("P1001", 5);
    }
}
