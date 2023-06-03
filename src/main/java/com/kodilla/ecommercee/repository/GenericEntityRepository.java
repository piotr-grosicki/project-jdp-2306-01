package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.GenericEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericEntityRepository extends CrudRepository<GenericEntity, Long> { }
