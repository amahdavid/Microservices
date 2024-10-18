package org.microservices.orderservice.service;

import org.microservices.orderservice.model.Order;
import org.microservices.orderservice.repository.OrderRepository;
import org.microservices.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order) {
        String USER_SERVICE_URL = "http://localhost:8081/users";
        User user = restTemplate.getForObject(USER_SERVICE_URL + "/" + order.getUserId(), User.class);
        return orderRepository.save(order);
    }
}
