package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class ProductEntityTests {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;

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

    @Test
    public void productAndCartRelationInitializing() {
        //Given
        Group group = Group.builder()
                .groupName("test group")
                .build();

        Product product = Product.builder()
                .productName("test product")
                .group(group)
                .build();

        User user = User.builder()
                .userName("test user")
                .build();

        Cart cart = Cart.builder()
                .user(user)
                .build();

        group.getProductList().add(product);

        //When
        Group savedGroup = groupRepository.save(group);
        user.cartList.add(cart);
        cart.getProductList().add(product);
        savedGroup.getProductList().get(0).getCartList().add(cart);
        userRepository.save(user);
        cartRepository.save(cart);

        //Then
        assertEquals(1, productRepository.findAll().size());
        assertEquals(1, cartRepository.findAll().get(0).getProductList().size());
        assertTrue(productRepository.findByProductName("test product").isPresent());
        assertEquals(productRepository.findByProductName("test product"), Optional.of(cartRepository.findAll().get(0).getProductList().get(0)));
    }

    @Test
    public void deletingProductDoesntDeleteGroupButUpdatesGroupsProductList() {
        //Given
        Group group = Group.builder()
                .groupName("test group")
                .build();

        Product product = Product.builder()
                .productName("test product")
                .group(group)
                .build();

        group.getProductList().add(product);
        groupRepository.save(group);

        //When
        productRepository.deleteAll();

        //Then
        assertTrue(groupRepository.findByGroupName("test group").isPresent());
        assertTrue(productRepository.findAll().isEmpty());
        assertEquals(0, groupRepository.findByGroupName("test group").get().getProductList().size());
    }

    @Test
    public void deletingProductDoesntDeleteCartNorUserButUpdatesCartsProductList() {
        //Given
        Group group = Group.builder()
                .groupName("test group")
                .build();

        Product product = Product.builder()
                .productName("test product")
                .group(group)
                .build();

        User user = User.builder()
                .userName("test user")
                .build();

        Cart cart = Cart.builder()
                .user(user)
                .build();

        group.getProductList().add(product);
        Group savedGroup = groupRepository.save(group);
        user.cartList.add(cart);
        cart.getProductList().add(product);
        savedGroup.getProductList().get(0).getCartList().add(cart);
        userRepository.save(user);
        Cart cart1 = cartRepository.save(cart);

        //When
        productRepository.deleteAll();

        //Then
        assertEquals(1, userRepository.findAll().size());
        assertEquals(1, cartRepository.findAll().size());
        assertTrue(productRepository.findAll().isEmpty());
        assertEquals(0, cartRepository.findAll().get(0).getProductList().size());
    }
}