package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    User save(User user);

    @Override
    Optional<User> findById(Long userId);

    @Override
    List<User> findAll();

    @Override
    void deleteById(Long userId);

    Optional<User> findUserByUserName(String name);
    List<User> findUsersByUserName(String name);
}