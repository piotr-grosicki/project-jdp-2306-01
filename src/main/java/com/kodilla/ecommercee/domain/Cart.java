package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "CARTS")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CART_ID")
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user;

    @ManyToMany(mappedBy = "cartList", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Builder.Default
    private List<Product> productList = new ArrayList<>();

    @PreRemove
    private void removeThisFromUser() {
        user.getCartList().remove(this);
        for (Product product : productList) {
            product.getCartList().remove(this);
        }
    }
}