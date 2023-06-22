package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
public class CartEntityTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    void testCartCreate() {

        //Given
        Group group = Group.builder()
                .groupName("group")
                .build();

        Product product = Product.builder()
                .productName("product")
                .group(group)
                .build();

        User user = User.builder()
                .userName("user")
                .build();

        Cart cart1 = Cart.builder()
                .user(user)
                .build();

        Cart cart2 = Cart.builder()
                .user(user)
                .build();

        group.getProductList().add(product);
        groupRepository.save(group);

        user.cartList.add(cart1);
        user.cartList.add(cart2);
        userRepository.save(user);

        product.getCartList().add(cart1);
        product.getCartList().add(cart2);
        productRepository.save(product);

        cart1.getProductList().add(product);
        cart2.getProductList().add(product);

        //When
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        //Then
        assertTrue(cartRepository.findById(cart1.getCartId()).isPresent());
        assertTrue(cartRepository.findById(cart2.getCartId()).isPresent());
        assertEquals(2, cartRepository.findAll().size());

        //Cleanup
        cartRepository.deleteById(cart1.getCartId());
        cartRepository.deleteById(cart2.getCartId());
        productRepository.deleteById(product.getProductId());
        userRepository.deleteById(user.getUserId());
        groupRepository.deleteById(group.getGroupId());
    }

    @Test
    void testCartRead() {

        //Given
        Group group = Group.builder()
                .groupName("group")
                .build();

        Product product = Product.builder()
                .productName("product")
                .group(group)
                .build();

        User user = User.builder()
                .userName("user")
                .build();

        Cart cart = Cart.builder()
                .user(user)
                .build();

        group.getProductList().add(product);
        groupRepository.save(group);

        user.cartList.add(cart);
        userRepository.save(user);

        product.getCartList().add(cart);
        productRepository.save(product);

        cart.getProductList().add(product);
        cartRepository.save(cart);

        //When

        Long cartId = cartRepository.findById(cart.getCartId()).get().getCartId();

        //Then
        assertTrue(groupRepository.findById(group.getGroupId()).isPresent());
        assertTrue(userRepository.findById(user.getUserId()).isPresent());
        assertTrue(productRepository.findById(product.getProductId()).isPresent());
        assertTrue(cartRepository.findById(cart.getCartId()).isPresent());
        assertEquals("user", cartRepository.findById(cart.getCartId()).get().getUser().getUserName());
        assertEquals("product", cartRepository.findById(cart.getCartId()).get().getProductList().get(0).getProductName());
        assertEquals(cartId, userRepository.findById(user.getUserId()).get().getCartList().get(0).getCartId());
        assertEquals(cartId, productRepository.findById(product.getProductId()).get().getCartList().get(0).getCartId());

        //Cleanup
        cartRepository.deleteById(cart.getCartId());
        productRepository.deleteById(product.getProductId());
        userRepository.deleteById(user.getUserId());
        groupRepository.deleteById(group.getGroupId());
    }

    @Test
    void testCartUpdate() {

        //Given
        Group group = Group.builder()
                .groupName("group")
                .build();

        Product product = Product.builder()
                .productName("product")
                .group(group)
                .build();

        User user = User.builder()
                .userName("user")
                .build();

        Cart cart1 = Cart.builder()
                .user(user)
                .build();

        Cart cart2 = Cart.builder()
                .user(user)
                .build();

        group.getProductList().add(product);
        groupRepository.save(group);

        user.cartList.add(cart1);
        user.cartList.add(cart2);
        userRepository.save(user);

        product.getCartList().add(cart1);
        productRepository.save(product);

        cart1.getProductList().add(product);
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        //When
        Group savedGroup = groupRepository.save(group);
        User savedUser = userRepository.save(user);
        Product savedProduct = productRepository.save(product);
        Cart savedCart = cartRepository.save(cart2);

        savedGroup.setGroupName("updatedGroup");
        savedUser.setUserName("updatedUser");
        savedProduct.setProductName("updatedProduct");
        savedProduct.getCartList().add(cart2);
        savedCart.getProductList().add(product);

        List<Product> updatedProducts = productRepository.findAll();
        List<Cart> updatedCarts = cartRepository.findAll();

        //Then
        assertEquals("updatedGroup", updatedCarts.get(0).getProductList().get(0).getGroup().getGroupName());
        assertEquals("updatedUser", updatedCarts.get(0).getUser().getUserName());
        assertEquals("updatedProduct", updatedCarts.get(0).getProductList().get(0).getProductName());
        assertEquals(2, updatedProducts.get(0).getCartList().size());
        assertEquals(1, updatedCarts.get(0).getProductList().size());
        assertEquals(1, updatedCarts.get(1).getProductList().size());

        //Cleanup
        cartRepository.deleteById(cart2.getCartId());
        productRepository.deleteById(product.getProductId());
        userRepository.deleteById(user.getUserId());
        groupRepository.deleteById(group.getGroupId());
    }
    @Test
    void testCartDelete() {

        //Given
        Group group = Group.builder()
                .groupName("group")
                .build();

        Product product = Product.builder()
                .productName("product")
                .group(group)
                .build();

        User user = User.builder()
                .userName("user")
                .build();

        Cart cart1 = Cart.builder()
                .user(user)
                .build();

        Cart cart2 = Cart.builder()
                .user(user)
                .build();

        group.getProductList().add(product);
        groupRepository.save(group);

        user.cartList.add(cart1);
        user.cartList.add(cart2);
        userRepository.save(user);

        product.getCartList().add(cart1);
        product.getCartList().add(cart2);
        productRepository.save(product);

        cart1.getProductList().add(product);
        cart2.getProductList().add(product);
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        //When
        int numberOfCarts = cartRepository.findAll().size();
        int cartsInUser = userRepository.findById(user.getUserId()).get().getCartList().size();
        int cartsInProduct = productRepository.findById(product.getProductId()).get().getCartList().size();

        cartRepository.deleteById(cart1.getCartId());

        List<User> updatedUsers = userRepository.findAll();
        List<Product> updatedProducts = productRepository.findAll();
        List<Cart> updatedCarts = cartRepository.findAll();

        //Then
        assertTrue(groupRepository.findById(group.getGroupId()).isPresent());
        assertTrue(userRepository.findById(user.getUserId()).isPresent());
        assertTrue(productRepository.findById(product.getProductId()).isPresent());
        assertTrue(cartRepository.findById(cart2.getCartId()).isPresent());
        assertEquals(2, cartsInUser);
        assertEquals(1, updatedUsers.get(0).getCartList().size());
        assertEquals(2, cartsInProduct);
        assertEquals(1, updatedProducts.get(0).getCartList().size());
        assertEquals(2, numberOfCarts);
        assertEquals(1, updatedCarts.size());

        //Cleanup
        cartRepository.deleteById(cart2.getCartId());
        productRepository.deleteById(product.getProductId());
        userRepository.deleteById(user.getUserId());
        groupRepository.deleteById(group.getGroupId());
    }
}
