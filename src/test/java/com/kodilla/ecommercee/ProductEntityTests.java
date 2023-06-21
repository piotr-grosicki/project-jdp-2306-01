package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductEntityTests {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GroupRepository groupRepository;

    @Test
    public void productAndGroupRelationInitializing() {
        //Given
        Group group = Group.builder()
                .groupName("test group")
                .build();

        Product product = Product.builder()
                .productName("test product")
                .group(group)
                .build();

        group.getProductList().add(product);

        //When
        groupRepository.save(group);

        //Then
        assertTrue(productRepository.findByProductName("test product").isPresent());
        assertTrue(groupRepository.findByGroupName("test group").isPresent());
        assertEquals(1, productRepository.findAll().size());
        assertEquals(1, groupRepository.findByGroupName("test group").get().getProductList().size());
    }
}