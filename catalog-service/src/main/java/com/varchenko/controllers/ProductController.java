package com.varchenko.controllers;

import com.varchenko.entity.Product;
import com.varchenko.exceptions.ProductNotFoundException;
import com.varchenko.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/products/")
@Slf4j
public class ProductController {

    private final ProductService productService;
    public static final AtomicInteger COUNTER_REQUEST = new AtomicInteger(0);
    public static final int DELAY_TIME = 3;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product productById(@PathVariable("id") String id) {
        networkDelay();
        return productService.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " doesn't exist"));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> productsBySku(@RequestParam String sku) {
        return productService.findProductsBySku(sku);
    }

    private void networkDelay() {
        if (COUNTER_REQUEST.incrementAndGet() % 7 == 0) {
            try {
                log.info("Simulate networks delay for: " + DELAY_TIME);
                TimeUnit.SECONDS.sleep(DELAY_TIME);
            } catch (InterruptedException e) {
                log.error("The catalog-service is interrupted while sleeping, should wait: " + DELAY_TIME);
            }
        }
    }
}
