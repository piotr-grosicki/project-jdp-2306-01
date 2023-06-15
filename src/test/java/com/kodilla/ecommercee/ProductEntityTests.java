package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductEntityTests {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GroupRepository groupRepository;

    @Test
    public void productGroupRelationsTest() {

        //Given
        Group group1 = Group.builder()
                .groupName("group1")
                .build();

        Product product2 = Product.builder()
                .productName("test product 2")
                .group(group1)
                .build();

        group1.getProductList().add(product2);

        //When
        groupRepository.save(group1);

        //Then
        assertFalse(productRepository.findAll().isEmpty());
        assertEquals(1, productRepository.findAll().size());
        assertEquals(product2.getProductName(), productRepository.findAll().get(0).getProductName());

        //Cleanup
        productRepository.deleteAll();
    }


}