package org.ordermicroservices.repository;

import org.ordermicroservices.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // custom query methods can be added here
}