package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMapper {
    @Autowired
    CartMapper cartMapper;
    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getOrderId(),
                cartMapper.mapToCart(orderDto.getCartDto()),
                orderDto.isSent()
        );
    }
    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getOrderId(),
                cartMapper.mapToCartDto(order.getCart()),
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
