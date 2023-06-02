package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(UserDto userDto) {
    }
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto blockUser(UserDto userDto) {
        return new UserDto();
    }
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void generateToken(UserDto userDto) {
    }
}
