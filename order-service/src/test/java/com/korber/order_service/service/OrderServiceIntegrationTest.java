package com.korber.order_service.service;


import com.korber.order_service.model.Order;
import com.korber.order_service.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testPlaceOrder_savesToDatabase() {
        Order order = new Order();
        order.setProductId("P100");
        order.setQuantity(20);

        Order saved = orderService.placeOrder(order);

        assertNotNull(saved.getId());
        assertEquals("P100", saved.getProductId());
        assertEquals(20, saved.getQuantity());
        assertEquals(LocalDate.now(), saved.getOrderDate());

        // check DB state
        assertEquals(1, orderRepository.count());
    }
}
