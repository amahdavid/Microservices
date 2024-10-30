package org.microservicetest.controller;

import org.junit.jupiter.api.*;
import org.ordermicroservices.model.Order;
import org.ordermicroservices.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        // Setup code if needed
    }

    @Test
    void testGetAllOrders() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetOrderById_OrderFound() throws Exception {
        Order order = new Order();
        order.setId(1L);
        when(orderService.getOrderById(1L)).thenReturn(order);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
    @Test
    void testGetOrderById_OrderNotFound() throws Exception {
        when(orderService.getOrderById(1L)).thenReturn(null);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateOrder_ValidOrder() throws Exception {
        Order order = new Order();
        order.setProduct("Test Product");
        order.setQuantity(5);
        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"product\":\"Test Product\",\"quantity\":5}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateOrder_InvalidOrder() throws Exception {
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"quantity\":-5}"))
                .andExpect(status().isBadRequest());
    }
}
