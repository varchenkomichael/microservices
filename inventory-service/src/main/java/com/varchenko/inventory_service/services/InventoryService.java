package com.varchenko.inventory_service.services;

import com.varchenko.inventory_service.entity.Inventory;
import com.varchenko.inventory_service.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<Inventory> findInventoriesByIds(List<String> ids) {
        return inventoryRepository.findByUniqIdIn(ids);
    }
}
