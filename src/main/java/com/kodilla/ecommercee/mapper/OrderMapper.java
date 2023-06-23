package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderMapper {
    @Autowired
    CartRepository cartRepository;    
    public Order mapToOrder(final OrderDto orderDto) {
        Optional<Cart> cart = cartRepository.findById(orderDto.getCartId());
        if (cart.isPresent()) {
            if (cart.get().getCartId() != null)
            {
            return new Order(
                orderDto.getOrderId(),
                cart.get(),
                orderDto.isSent());
            }
            else {
             //make throw here
            return new Order(1L,new Cart(), false);                
            }
        }
        else {
            //make throw here
            return new Order(1L,new Cart(), false); 
        }        
    }
    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getCart().getCartId(),
                order.isSent()
        );
    }
    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .toList();
    }
    public List<Order> mapToOrderList(final List<OrderDto> orderDtoList) {
        return  orderDtoList.stream()
                .map(this::mapToOrder)
                .toList();
    }
}
