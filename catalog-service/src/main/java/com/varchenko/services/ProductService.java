package com.varchenko.services;

import com.varchenko.entity.Product;
import com.varchenko.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<Product> findProductById(String id) {
        return productRepository.findByUniqId(id);
    }

    public List<Product> findProductsBySku(String sku) {
        return productRepository.findProductsBySku(sku);
    }
}
