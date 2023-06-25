package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserFullDto {
    private Long userId;
    private String userName;
    private String userToken;
    private Timestamp userTokenValid;
    private boolean isUserBlocked;
    private List<Long> cartIdList;
}