package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "CARTS")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CART_ID")
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToMany(mappedBy = "cartList", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Builder.Default
    private List<Product> productList = new ArrayList<>();

    @PreRemove
    private void removeThisFromRelations() {
        user.getCartList().remove(this);
        for (Product product : productList) {
            product.getCartList().remove(this);
        }
    }
}