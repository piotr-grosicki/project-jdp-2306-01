package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class CartMapper {
    public Cart mapToCart(final CartDto cartDto) {
        return new Cart();
    }
    public CartDto mapToCartDto(final @NotNull Cart cartDto) {
        return new CartDto(1L, new UserDto(1L, "1"));
    }
}