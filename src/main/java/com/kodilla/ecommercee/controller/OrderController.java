package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@CrossOrigin("*")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders() {
        return new ArrayList<>();

    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return new OrderDto(1L, true);

    }

    @GetMapping(value = "{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) {
        return new OrderDto(1L, true );

    }

    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return new OrderDto(1L, true);
    }

    @DeleteMapping(value = "{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
    }

}