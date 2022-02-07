package com.varchenko.inventory_service.repositories;

import com.varchenko.inventory_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {

    List<Inventory> findByUniqIdIn(List<String> ids);

}
