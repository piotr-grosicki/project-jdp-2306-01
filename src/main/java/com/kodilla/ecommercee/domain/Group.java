package com.kodilla.ecommercee.domain;

import javax.persistence.*;


@Entity(name = "GROUPS")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID")
    private Long groupId;
}
