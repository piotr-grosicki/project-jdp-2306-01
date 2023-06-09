package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Data
@AllArgsConstructor
@Entity(name = "PRODUCT_GROUPS")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID")
    private Long groupId;

    @Column(name = "GROUP_NAME", unique = true)
    @NonNull
    private String groupName;

    @OneToMany
    @JoinTable(
            name="CUST_PHONE",
            joinColumns=
            @JoinColumn(name="GROUP_ID", referencedColumnName="GROUP_ID"),
            inverseJoinColumns=
            @JoinColumn(name="PRODUCT_ID", referencedColumnName="PRODUCT_ID")
    )
    private List<Product> productList;

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public Group(String groupName, List<Product> productList) {
        this.groupName = groupName;
        this.productList = productList;
    }
}
