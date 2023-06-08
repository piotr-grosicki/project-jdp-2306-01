package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Getter
@Data
@AllArgsConstructor
@Entity(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PRODUCT_ID", unique=true)
    private Long productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable (
            name = "JOIN_PRODUCT_CART",
            joinColumns = {
            @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {
            @JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")}
    )
    private List<Cart> getCartList = new ArrayList<>();
    public Product (String productName) {
        this.productName = productName;
    }
}
