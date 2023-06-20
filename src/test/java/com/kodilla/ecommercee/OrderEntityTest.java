package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderEntityTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void OrderAndCartRelationInitializing() {

        //Given
        User user = User.builder()
                .userName("test user")
                .build();

        Group group = Group.builder()
                .groupName("test group")
                .build();

        Product product = Product.builder()
                .productName("test product")
                .group(group)
                .build();

        Cart cart = Cart.builder()
                .user(user)
                .build();

        Order order = Order.builder()
                .cart(cart)
                .build();

        //When
        user.cartList.add(cart);
        userRepository.save(user);
        groupRepository.save(group);
        cartRepository.save(cart);
        orderRepository.save(order);
        group.getProductList().add(product);
        product.getCartList().add(cart);
        cart.getProductList().add(product);
        order.setCart(cart);


        //Then
        assertTrue(orderRepository.findById(order.getOrderId()).isPresent());
        assertEquals(1, orderRepository.findAll().size());

    }

}
