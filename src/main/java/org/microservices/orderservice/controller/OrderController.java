package org.microservices.orderservice.controller;

import org.microservices.orderservice.exceptions.*;
import org.microservices.orderservice.model.Order;
import org.microservices.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            throw new OrderNotFoundException("Order with ID " + id + " not found.");
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
        if (order.getProduct() == null || order.getQuantity() <= 0) {
            throw new InvalidOrderException("Invalid order details: Product cannot be null and quantity must be greater than zero.");
        }
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
}
