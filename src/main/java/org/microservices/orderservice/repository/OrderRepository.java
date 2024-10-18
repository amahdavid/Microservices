package org.microservices.orderservice.repository;

import org.microservices.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // custom query methods can be added here
}
