package com.kodilla.ecommercee.domain.dto;

import lombok.*;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
}