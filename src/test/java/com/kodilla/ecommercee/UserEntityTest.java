package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest()
@Transactional
public class UserEntityTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testUserCreation1() {
        //Given
        User user1 = User.builder().userName("test user").build();
        //When
        userRepository.save(user1);
        Optional<User> retrievedUser = userRepository.findUserByUserName("test user");
        //Then
        assertTrue(retrievedUser.isPresent());
        assertEquals(user1.getUserName(),retrievedUser.get().getUserName());
    }
    @Test
    public void testUserCreation2() {
        //Given
        User user2 = User.builder().userName("test user2").isUserBlocked(true).userToken("test token").userTokenValid(LocalDate.now()).build();
        //When
        userRepository.save(user2);
        Optional<User> retrievedUser2 = userRepository.findUserByUserName("test user2");
        //Then
        assertTrue(retrievedUser2.isPresent());
        assertEquals(user2.getUserName(),retrievedUser2.get().getUserName());
    }
    @Test
    public void testUserCreation3() {
        //Given
        User user3 = User.builder()
                .userName("test user3")
                .isUserBlocked(false)
                .userToken("test token")
                .userTokenValid(LocalDate.now())
                .cartList(new ArrayList<>())
                .build();
        //When
        userRepository.save(user3);
        Optional<User> retrievedUser3 = userRepository.findUserByUserName("test user3");
        //Then
        assertTrue(retrievedUser3.isPresent());
        assertEquals(user3.getUserName(),retrievedUser3.get().getUserName());
    }

    @Test
    public void testUserCreation4() {
        //Given
        User user4 = User.builder()
                .userName("test user4")
                .build();
        //When
        userRepository.save(user4);
        List<User> retrievedUsers = userRepository.findUsersByUserName("test user4");
        //Then
        assertEquals(retrievedUsers.get(0).getUserName(),"test user4");
        assertEquals(retrievedUsers.size(),1);
    }
    @Test
    public void testUserCascadeDelete(){
        //Given
        User user = User.builder()
                .userName("test user")
                .build();
        Cart cart = Cart.builder().user(user).build();
        user.cartList.add(cart);
        //When
        userRepository.save(user);
        cartRepository.save(cart);
        userRepository.deleteAll();
        List<User> retrievedUsers = userRepository.findUsersByUserName("test user");
        List<Cart> retrievedCarts = cartRepository.findAll();
        //Then
        assertEquals(retrievedUsers.size(),0);
        assertEquals(retrievedCarts.size(), 1);
    }
}
