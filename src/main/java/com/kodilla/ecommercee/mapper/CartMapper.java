package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
@RequiredArgsConstructor
@Service
public class CartMapper {
    private final UserService userService;
    public Cart mapToCart(final CartDto cartDto) throws UserNotFoundException {
        return Cart.builder()
                .user(userService.findUserById(cartDto.getUserId()))
                .build();
    }
    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getCartId(),
                cart.getUser().getUserId()
        );
    }
}