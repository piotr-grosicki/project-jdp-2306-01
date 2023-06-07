package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.CartProductDto;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @PostMapping(value = "/new")
    public CartDto createCart(@RequestBody UserDto userDto) {
        return new CartDto(1L, 1L);
    }

    @GetMapping(value = "{cartId}")
    public List<CartProductDto> getCart(@PathVariable Long cartId) {
        return new ArrayList<>();
    }

    @PostMapping(value = "{cartId}/{productId}")
    public CartProductDto addCartProduct(@PathVariable Long cartId, @PathVariable Long productId) {
        return new CartProductDto(1L, 1L);
    }

    @DeleteMapping(value = "{cartId}/{productId}")
    public void deleteCartProduct(@PathVariable Long cartId, @PathVariable Long productId) {
    }

    @PostMapping(value = "/order")
    public OrderDto createOrder(@RequestBody CartDto cartDto) {
        return new OrderDto(1L, false);
    }
}