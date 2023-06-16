package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class GroupEntityTests {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;


    @Test
    public void testAddProductToGroup() {
        //GIVEN

        Group group1 = new Group();
        groupRepository.save(group1);

        Product product1 = new Product();
        Product product2 = new Product();
        productRepository.save(product1);
        productRepository.save(product2);
        group1.getProductList().add(product1);
        group1.getProductList().add(product2);

        Long id1 = group1.getGroupId();

        //WHEN
        int testProductsInGroupSize = group1.getProductList().size();

        //THEN
        assertEquals(2, testProductsInGroupSize);
        //CLEAN UP
        groupRepository.deleteById(id1);
        productRepository.deleteAll();

    }
    @Test
    public void testUpdateGroupData(){
        //GIVEN
        Group group1 = new Group();
        groupRepository.save(group1);

        //WHEN
        Long id1 = group1.getGroupId();
        Group groupById1 = groupRepository.findById(id1).orElse(new Group());
        groupById1.setGroupName("ChangedName");
        groupRepository.save(groupById1);

        //THEN
        System.out.println(groupRepository.findById(id1).get().getGroupName());
        assertEquals("ChangedName", groupRepository.findById(id1).get().getGroupName());
        //CLEANUP
        groupRepository.deleteAll();
    }

}