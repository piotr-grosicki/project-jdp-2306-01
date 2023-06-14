package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartEntityTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testFindCartById() {

        //Given
        User user = new User();
        userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

        //When
        Optional<Cart> foundCarts = cartRepository.findById(cart.getCartId());

        //Then
        assertTrue(foundCarts.isPresent());
        assertEquals(user.getUserId(), foundCarts.get().getUser().getUserId());
        assertEquals(cart.getCartId(), foundCarts.get().getCartId());

        //Cleanup
        cartRepository.deleteAll();
        userRepository.deleteAll();
    }
    @Test
    public void testFindAllCarts() {

        //Given
        User user1 = new User();
        User user2 = new User();
        userRepository.save(user1);
        userRepository.save(user2);

        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        cart1.setUser(user1);
        cart2.setUser(user2);
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        //When
        List<Cart> foundCarts = cartRepository.findAll();

        //Then
        assertFalse(foundCarts.isEmpty());
        assertEquals(2, foundCarts.size());
        assertEquals(cart1.getCartId(), foundCarts.get(0).getCartId());
        assertEquals(cart2.getCartId(), foundCarts.get(1).getCartId());

        //Cleanup
        cartRepository.deleteAll();
        userRepository.deleteAll();
    }
}
