package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.domain.dto.UserFullDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMapper {

    public User mapToUser(final UserDto userDto){
        return User.builder()
                .userId(userDto.getUserId())
                .userName(userDto.getUserName())
                .build();
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getUserName());
    }

        public UserFullDto mapToUserFullDto(final User user){
        return new UserFullDto(
                user.getUserId(),
                user.getUserName(),
                user.getUserToken(),
                user.getUserTokenValid(),
                user.isUserBlocked(),
                user.getCartList().stream()
                        .map(Cart::getCartId)
                        .toList()
        );
    }

    public List<UserFullDto> mapToUserFullDtoList(final List<User> userList){
        return userList.stream()
                .map(this::mapToUserFullDto)
                .toList();
    }
}