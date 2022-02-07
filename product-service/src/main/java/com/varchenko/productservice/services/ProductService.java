package com.varchenko.productservice.services;

import com.varchenko.productservice.entity.Inventory;
import com.varchenko.productservice.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final RestTemplate restTemplate;
    private static final String CATALOG_SERVICE = "http://CATALOG-SERVICE/api/products/";
    public static final String INVENTORY_SERVICE = "http://INVENTORY-SERVICE/api/inventories";

    public Optional<Product> availableProductById(String id) {
        Product product = restTemplate.getForObject(CATALOG_SERVICE + "{id}", Product.class, id);
        ResponseEntity<Inventory[]> inventoryList = restTemplate
                .getForEntity(INVENTORY_SERVICE + "?ids={id}", Inventory[].class, id);
        Inventory[] inventories = inventoryList.getBody();
        if (product != null && inventories != null) {
            for (Inventory inventory : inventories) {
                if (inventory.getAvailability() != 0 && inventory.getUniqId().equals(product.getUniqId())) {
                    return Optional.of(product);
                }
            }
        } else {
            return Optional.empty();
        }
        return Optional.empty();
    }
}
