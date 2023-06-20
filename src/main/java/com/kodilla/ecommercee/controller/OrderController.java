package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@CrossOrigin("*")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orders));
    }
    @PostMapping(value = "/new",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        orderService.saveOrder(order);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return ResponseEntity.ok(orderMapper.mapToOrderDto(orderService.getOrder(orderId)));
    }
    @PutMapping
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        Order savedOrder = orderService.saveOrder(order);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(savedOrder));
    }
    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}