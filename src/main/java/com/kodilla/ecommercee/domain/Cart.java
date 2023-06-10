package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "CARTS")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "CART_ID")
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartList")
    private List<Product> productList = new ArrayList<>();
}

