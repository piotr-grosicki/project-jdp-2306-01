package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/v1/carts")
public class CartController {
    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final CartService cartService;




    @PostMapping(value = "/new/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDto> createCart(@RequestBody Long userId) throws UserNotFoundException {
        User user = userService.findUserById(userId);
        Cart cart = Cart.builder()
                .user(user)
                .build();
        cartService.saveCart(cart);
        return  ResponseEntity.ok(cartMapper.mapToCartDto(cart));
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long cartId) throws CartNotFoundException {
        return ResponseEntity.ok(cartMapper.mapToCartDto(cartService.getCart(cartId)));
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
    public ResponseEntity<OrderDto> createOrder(@RequestBody CartDto cartDto) throws UserNotFoundException {
        Order order = Order.builder()
                        .cart(cartMapper.mapToCart(cartDto)).build();
        return  ResponseEntity.ok(orderMapper.mapToOrderDto(order));

    }
}