package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exceptions.UserBlockedException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }

    public User findUserById(Long userId) throws UserNotFoundException {
        return repository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public void generateToken(Long userId) throws UserNotFoundException, UserBlockedException{
        User updatedUser = repository.findById(userId).orElseThrow(UserNotFoundException::new);
        if(updatedUser.isUserBlocked()) throw new UserBlockedException();
        updatedUser.setUserToken(UUID.randomUUID().toString());
        updatedUser.setUserTokenValid(new Timestamp(System.currentTimeMillis()+3600*1000));
        repository.save(updatedUser);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public void blockUser(Long userId) throws UserNotFoundException {
        User blockedUser = repository.findById(userId).orElseThrow(UserNotFoundException::new);
        blockedUser.setUserBlocked(true);
        repository.save(blockedUser);
    }

    public void deleteById(Long userId) throws UserNotFoundException {
        User toBeDeletedUser = repository.findById(userId).orElseThrow(UserNotFoundException::new);
        repository.delete(toBeDeletedUser);
    }

    public User findUserByUserName(String name) throws UserNotFoundException {
        return repository.findUserByUserName(name).orElseThrow(UserNotFoundException::new);
    }
}