package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "\"GROUP\"")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long groupId;

    @Column(name = "GROUP_NAME", unique = true)
    @NotNull
    private String groupName;

    @OneToMany(targetEntity = Product.class,
            mappedBy = "group",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    @Builder.Default
    public List<Product> productList = new ArrayList<>();

    @PreRemove
    private void preRemove() {
        productList.forEach(productList -> productList.setGroup(null));
    }
}