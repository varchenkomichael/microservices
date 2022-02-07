package com.varchenko.repositories;

import com.varchenko.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByUniqId(String id);

    List<Product> findProductsBySku(String sku);

}
