package com.kodilla.ecommercee.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private Long orderId;
    private Long cartId;
    private boolean isSent;
}

