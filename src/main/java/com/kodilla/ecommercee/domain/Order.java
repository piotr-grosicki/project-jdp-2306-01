package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    private Long orderId;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @NotNull
    @Column(name = "ORDER_SENT")
    private boolean isSent;

    public Order() {
    }

    public Order(Long orderId, boolean isSent) {
        this.orderId = orderId;
        this.isSent = isSent;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Cart getCart() {
        return cart;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }
}
