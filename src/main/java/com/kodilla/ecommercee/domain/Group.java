package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "GROUPS")
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID")
    private Long groupId;

    @Column(name = "GROUP_NAME")
    private String groupName;
    public ProductGroup (String groupName) {
        this.groupName = groupName;
    }

    @OneToMany(
            targetEntity = Product.class,
            cascade = CascadeType.ALL,
            mappedBy = "productGroup",
            fetch = FetchType.LAZY
    )
    public List<Product> getGroupProductsList = new ArrayList<>();

}
