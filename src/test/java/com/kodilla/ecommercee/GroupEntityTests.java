package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Component
@Service
public class GroupEntityTests {
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
    public void testUpdateGroupData() {
        // Given
        Group group = Group.builder()
                .groupName("test group")
                .build();

        Group updatedGroup = Group.builder()
                .groupName("updated group")
                .build();

        // When
        Group savedGroup = groupRepository.save(group);
        savedGroup.setGroupName(updatedGroup.getGroupName());
        Group updatedSavedGroup = groupRepository.save(savedGroup);

        // Then
        assertEquals(updatedGroup.getGroupName(), updatedSavedGroup.getGroupName());
        assertEquals(savedGroup.getGroupId(), updatedSavedGroup.getGroupId());
    }
}