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
    public void productCreationTest() {

        //Given
        Product product1 = Product.builder()
                .productId(1L)
                .productName("test product 1")
                .build();

        //When
        long iD = productRepository.save(product1).getProductId();
        Optional<Product> retrievedProduct = productRepository.findById(iD);

        //Then
        assertTrue(retrievedProduct.isPresent());
        assertEquals(product1.getProductName(), retrievedProduct.get().getProductName());

        //Cleanup
        productRepository.deleteAll();
    }

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

    @Test
    public void productCartRelationsTest() {

        //Given
        Product product3 = Product.builder()
                .productName("test product 3")
                .build();

        Cart cart = new Cart();
        product3.getCartList().add(cart);
        cart.getProductList().add(product3);

        //When
        long cartId = cartRepository.save(cart).getCartId();

        //Then
        assertFalse(productRepository.findAll().isEmpty());
        assertEquals(1, productRepository.findAll().size());
        assertEquals(cartId, productRepository.findAll().get(0).getCartList().get(0).getCartId());

        //Cleanup
        productRepository.deleteAll();
    }
}