package com.kodilla.ecommercee.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "USER_NAME")
    @NotNull
    private String userName;

    @Column(name = "USER_TOKEN")
    private String userToken;

    @Column(name = "USER_TOKEN_VALID")
    private LocalDate userTokenValid;

    @Column(name = "USER_BLOCKED")
    @NotNull
    private boolean isUserBlocked;

    @OneToMany(targetEntity = Cart.class,
            mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.EAGER,
            orphanRemoval = true)

    @Builder.Default
    public List<Cart> cartList = new ArrayList<>();
}