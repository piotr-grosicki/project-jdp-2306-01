package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto createCart(@RequestBody UserDto userDto) {
        return new CartDto(1L, new UserDto(1L));
    }

    @GetMapping(value = "{cartId}")
    public List<ProductDto> getCart(@PathVariable Long cartId) {
        return new ArrayList<>();
    }

    @PostMapping(value = "{cartId}/{productId}")
    public List<ProductDto> addCartProduct(@PathVariable Long cartId, @PathVariable Long productId)  {
        return new ArrayList<>();
    }

    @DeleteMapping(value = "{cartId}/{productId}")
    public List<ProductDto> deleteCartProduct(@PathVariable Long cartId, @PathVariable Long productId) {
        return new ArrayList<>();
    }

    @PostMapping(value = "/carts", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto createCart(@RequestBody CartDto cartDto) {
        return new CartDto(1L, new UserDto(1L));
    }
}