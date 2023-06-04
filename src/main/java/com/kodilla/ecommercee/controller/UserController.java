package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {
    @GetMapping
    public List<UserDto> getUsers() {
        return new ArrayList<>();
    }
    @GetMapping(value = "{userId}")
    public UserDto getUser(@PathVariable Long orderId) {
        return new UserDto(1L);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(UserDto userDto) {
    }
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto blockUser(UserDto userDto) {
        return new UserDto(1L);
    }
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void generateToken(UserDto userDto) {
    }
    @DeleteMapping
    public  void deleteUser(Long userId) {
    }
}
