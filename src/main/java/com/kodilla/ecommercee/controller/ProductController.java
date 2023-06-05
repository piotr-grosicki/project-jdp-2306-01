package com.kodilla.ecommercee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public ResponseEntity<List<Object>> getProducts() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping(value = "{productId}")
    public ResponseEntity<Object> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(new Object());
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Object productDto) {
        return ResponseEntity.ok(new Object());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody Object productDto) {
        return ResponseEntity.ok().build();
    }
}
