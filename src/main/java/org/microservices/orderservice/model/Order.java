package org.microservices.orderservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId; // Foreign key to associate order with a user
    private String product;
    private Integer quantity;

    // Constructors
    public Order() {}
    public Order(Long userId, String product, Integer quantity) {
        this.userId = userId;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Long getUserId() {return userId;}

    public void setUserId(Long userId) {this.userId = userId;}

    public String getProduct() {return product;}

    public void setProduct(String product) {this.product = product;}

    public Integer getQuantity() {return quantity;}

    public void setQuantity(Integer quantity) {this.quantity = quantity;}
}
