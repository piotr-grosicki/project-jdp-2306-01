package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import com.kodilla.ecommercee.domain.Order;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public Order saveOrder(final Order order) {
        return orderRepository.save(order);
    }
    public Order getOrder(final Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }
    public void deleteOrder(final Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
