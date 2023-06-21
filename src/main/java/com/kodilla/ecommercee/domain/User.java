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
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
            fetch = FetchType.EAGER
            )

    @Builder.Default
    public List<Cart> cartList = new ArrayList<>();
    @PreRemove
    private void preRemove() {
        cartList.forEach(cartList -> cartList.setUser(null));
    }
}