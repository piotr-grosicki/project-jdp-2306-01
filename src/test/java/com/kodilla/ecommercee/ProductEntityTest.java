package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductEntityTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GroupRepository groupRepository;

    @Test
    public void productCreationTest() {
        //Given
        Product product1 = Product.builder()
                .productId(1L)
                .productName("test product 1")
                .build();
        //When
        productRepository.save(product1);
        Optional<Product> retrievedProduct = productRepository.findById(1L);
        //Then
        assertTrue(retrievedProduct.isPresent());
        assertEquals(product1.getProductName(), retrievedProduct.get().getProductName());

    }
}
