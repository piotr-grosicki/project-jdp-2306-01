package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.*;

@Entity(name = "CARTS")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CART_ID")
    private Long cartId;i

}
