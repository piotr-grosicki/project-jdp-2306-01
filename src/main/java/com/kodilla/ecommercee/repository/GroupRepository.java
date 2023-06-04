package com.kodilla.ecommercee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.acl.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}