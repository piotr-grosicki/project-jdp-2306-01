package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.UserBlockedException;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.domain.dto.UserFullDto;
import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping(value = "{userId}")
    public ResponseEntity<UserFullDto> getUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserFullDto(userService.findUserById(userId)));
    }

    @GetMapping
    public ResponseEntity<List<UserFullDto>> getUsers() {
        return ResponseEntity.ok(userMapper.mapToUserFullDtoList(userService.getUsers()));
    }

    @PostMapping(value ="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
        userService.saveUser(userMapper.mapToUser(userDto));
        return ResponseEntity.ok("New user " + userDto.getUserName() + " was successfully created");
    }

    @PutMapping(value ="/token/{userId}")
    public ResponseEntity<UserFullDto> generateToken(@PathVariable Long userId) throws UserNotFoundException, UserBlockedException {
        userService.generateToken(userId);
        return ResponseEntity.ok(userMapper.mapToUserFullDto(userService.findUserById(userId)));
    }

    @PutMapping(value ="/block/{userId}")
    public ResponseEntity<Object> blockUser(@PathVariable Long userId) throws UserNotFoundException {
        userService.blockUser(userId);
        return ResponseEntity.ok("User " + userService.findUserById(userId).getUserName() + " was successfully blocked");
    }

    @DeleteMapping(value = "/delete/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        String userName = userService.findUserById(userId).getUserName();
        userService.deleteById(userId);
        return ResponseEntity.ok("User " + userName + " was successfully deleted");
    }
}