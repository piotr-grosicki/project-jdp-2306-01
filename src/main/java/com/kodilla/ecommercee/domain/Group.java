package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
@Entity(name = "GROUP")
@javax.persistence.Table(name = "\"GROUP\"")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long groupId;

    @Column(name = "GROUP_NAME", unique = true)
    @NonNull
    private String groupName;

    private List<Product> products = new ArrayList<>();

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "\"GROUP\"",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Product> getProducts() {
        return products;
    }
}
