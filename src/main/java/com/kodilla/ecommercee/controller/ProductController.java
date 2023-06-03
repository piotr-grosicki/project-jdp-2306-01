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
    public ResponseEntity<List<Object>> getCopyOfBooks() {
        return ResponseEntity.ok(new ArrayList<>());
    }
    @GetMapping(value = "{productId}")
    public ResponseEntity<Object> getCopyOfBook(@PathVariable Long productId) {
        return ResponseEntity.ok(new Object());
    }
    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteCopyOfBook(@PathVariable Long productId) {
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<Object> updateCopyOfBook(@RequestBody Object productDto) {
        return ResponseEntity.ok(new Object());
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCopyOfBook(@RequestBody Object productDto) {
        return ResponseEntity.ok().build();
    }
}
