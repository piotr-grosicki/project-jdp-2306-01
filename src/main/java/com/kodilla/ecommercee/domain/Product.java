package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID", unique = true)
    private Long productId;

    @Column(name = "PRODUCT_NAME")
    @NotNull
    private String productName;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    @NotNull
    private Group group;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "JOIN_PRODUCT_CART",
            joinColumns = {
                    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")}
    )
    @Builder.Default
    private List<Cart> cartList = new ArrayList<>();

    @PreRemove
    public void removeThisFromRelations() {
        group.getProductList().remove(this);
        for (Cart cart : cartList) {
            cart.getProductList().remove(this);
        }
    }

