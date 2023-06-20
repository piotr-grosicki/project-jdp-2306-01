package com.kodilla.ecommercee.domain.dto;

import lombok.*;

@AllArgsConstructor
@Data
public class OrderDto {
    private Long orderId;
    private CartDto cartDto;
    private boolean isSent;
}

