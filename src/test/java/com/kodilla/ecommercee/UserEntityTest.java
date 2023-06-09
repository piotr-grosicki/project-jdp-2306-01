package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest()
public class UserEntityTest {
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Test
    public void givenUserEntityRepository_whenSaveAndRetrieveEntity_thenOK() {
        //Given
        User user1 = new User(10000L,"test user","test Token", LocalDate.now(), false, new ArrayList<>());
        //When
        userEntityRepository.save(user1);
        Optional<User> retrievedUser = userEntityRepository.findUserByUserName("test user");
        //Then
        assertTrue(retrievedUser.isPresent());
        assertEquals(user1.getUserName(),retrievedUser.get().getUserName());
    }
}
