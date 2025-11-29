package com.korber.order_service.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.korber.order_service.model.Order;
import com.korber.order_service.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testPlaceOrder() throws Exception {
        // Input request
        Order request = new Order();
        request.setProductId("P001");
        request.setQuantity(10);

        // Mock output
        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setProductId("P001");
        savedOrder.setQuantity(10);
        savedOrder.setOrderDate(LocalDate.now());

        // Mock service behavior
        Mockito.when(orderService.placeOrder(Mockito.any(Order.class)))
                .thenReturn(savedOrder);

        // Perform API call
        mockMvc.perform(post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productId").value("P001"))
                .andExpect(jsonPath("$.quantity").value(10));
    }
}
