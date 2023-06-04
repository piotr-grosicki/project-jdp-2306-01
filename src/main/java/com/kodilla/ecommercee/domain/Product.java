package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Entity(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PRODUCT_ID", unique=true)
    private Long productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;
    public Product (String productName) {
        this.productName = productName;
    }

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    public Group getGroup = new Group();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "PRODUCTS")
    @JoinTable (name = "JOIN_PRODUCT_CART",
    joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
    inverseJoinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")}
    )
    public List<Cart> getCarts = new ArrayList<>();

}
