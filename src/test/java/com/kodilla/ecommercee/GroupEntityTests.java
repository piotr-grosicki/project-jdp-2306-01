package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
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

        // When
        Group savedGroup = groupRepository.save(group);
        savedGroup.setGroupName("updated group");
        groupRepository.save(savedGroup);

        // Then
        assertEquals(1,groupRepository.findAll().size());
        assertEquals("updated group",groupRepository.findAll().get(0).getGroupName());
    }
}