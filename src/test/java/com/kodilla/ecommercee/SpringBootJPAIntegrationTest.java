package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.GenericEntity;
import com.kodilla.ecommercee.repository.GenericEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest()
public class SpringBootJPAIntegrationTest {

    @Autowired
    private GenericEntityRepository genericEntityRepository;

    @Test
    public void givenGenericEntityRepository_whenSaveAndRetrieveEntity_thenOK() {
        //Given
        //When
        GenericEntity genericEntity = genericEntityRepository
                .save(new GenericEntity("test"));
        Optional<GenericEntity> foundEntity = genericEntityRepository
                .findById(genericEntity.getId());
        //Then
        assertTrue(foundEntity.isPresent());
        assertEquals(genericEntity.getValue(), foundEntity.get().getValue());
    }
}
