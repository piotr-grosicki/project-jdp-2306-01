package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
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
    public UserDto getUser(@PathVariable Long userId) {
        return new UserDto(1L);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto blockUser(@RequestBody UserDto userDto) {
        return new UserDto(1L);
    }

    @PutMapping(value ="/token", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void generateToken(@RequestBody UserDto userDto) {
    }

    @DeleteMapping(value = "{userId}")
    public void deleteUser(@PathVariable Long userId) {
    }
}