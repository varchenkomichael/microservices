package com.varchenko.productservice.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.varchenko.productservice.entity.Product;
import com.varchenko.productservice.exceptions.ProductNotFoundException;
import com.varchenko.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("{id}")
    @HystrixCommand(commandKey = "available-product-by-id", fallbackMethod = "productByIdFallBack")
    public ResponseEntity<Product> availableProductById(@PathVariable String id) {
        return productService.availableProductById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ProductNotFoundException("Product with " + id + " doesn't exist"));
    }

    private ResponseEntity<Product> productByIdFallBack(String id, Throwable throwable) {
        log.error("Product-service can't return product with id: " + id, throwable);
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
}

