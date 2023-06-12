package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;


    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_TOKEN")
    private String userToken;

    @Column(name = "USER_TOKEN_VALID")
    private LocalDate userTokenValid;

    @Column(name = "USER_BLOCKED")
    private boolean isUserBlocked;

    @OneToMany(targetEntity = Cart.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    public List<Cart> cartList = new ArrayList();
}

