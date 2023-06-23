package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.GroupController;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Data
public class ProductService {
    private final ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }
    public Product getProductById(final Long Id)throws ProductNotFoundException {
        return repository.findById(Id).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(final Product product) {
        return repository.save(product);
    }

    public void deleteProduct(final Long productId) {
        repository.deleteById(productId);
    }
}