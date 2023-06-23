package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/Products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final ProductService service;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = service.getAllProducts();
        return ResponseEntity.ok(productMapper.mapToProductDtoList(products));
    }
    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productMapper.mapToProductDto(service.getProductById(productId)));
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        Product updateProduct = service.saveProduct(product);
        return ResponseEntity.ok(productMapper.mapToProductDto(updateProduct));
    }

    @DeleteMapping(path = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        service.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}