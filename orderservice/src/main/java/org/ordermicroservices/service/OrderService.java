package org.ordermicroservices.service;

import org.ordermicroservices.dto.UserDTO;
import org.ordermicroservices.model.Order;
import org.ordermicroservices.repository.OrderRepository;
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
        UserDTO user = restTemplate.getForObject(USER_SERVICE_URL + "/" + order.getUserId(), UserDTO.class);
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + order.getUserId());
        }
        return orderRepository.save(order);
    }
}