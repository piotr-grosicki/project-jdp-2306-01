package com.kodilla.ecommercee.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    @JsonProperty("cartId")
    private Long cartId;

    @JsonProperty("userId")
    private Long userId;
}