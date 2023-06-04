package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.CartItemDto;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @PostMapping
    public CartDto createCart(@RequestBody UserDto userDto) {
        return new CartDto(1L, 1L);
    }

    @GetMapping(value = "{cartId}")
    public List<CartItemDto> getCart(@PathVariable Long cartId) {
        return new ArrayList<>();
    }

    @PostMapping(value = "{productId}")
    public CartItemDto addCartItem(@PathVariable Long productId) {
        return new CartItemDto(1L, 1L);
    }

    @DeleteMapping(value = "{cartItemId}")
    public void deleteCartItem(@PathVariable Long cartItemId) {
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody CartDto cartDto) {
        return new OrderDto(1L,1L, false);
    }

}
