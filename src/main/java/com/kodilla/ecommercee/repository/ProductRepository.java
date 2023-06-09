package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Product;
import lombok.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @Override
    List<Product> findAll();

    @Override
    Product save(Product product);

    @Override
    Optional<Product> findById(Long productId);

    @Override
    void deleteById(Long productId);

    @Override
    void deleteAll();

    Optional<Product> findByProductName(String name);
}
