package com.kodilla.ecommercee.domain.dto;

import lombok.*;


@AllArgsConstructor
@Data
public class CartDto {
    private Long cartId;
    private UserDto userDto;
}