package com.varchenko.inventory_service.controllers;

import com.varchenko.inventory_service.entity.Inventory;
import com.varchenko.inventory_service.services.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@Slf4j
public class InventoryController {

    public static final AtomicInteger COUNTER_REQUEST = new AtomicInteger(0);
    public static final int DELAY_TIME = 3;
    private final InventoryService inventoryService;

    @GetMapping(value = "/inventories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Inventory> statusByIds(@RequestParam List<String> ids) {
        networkDelay();
        return inventoryService.findInventoriesByIds(ids);
    }

    private void networkDelay() {
        if (COUNTER_REQUEST.incrementAndGet() % 5 == 0) {
            try {
                log.info("Simulate networks delay for: " + DELAY_TIME);
                TimeUnit.SECONDS.sleep(DELAY_TIME);
            } catch (InterruptedException e) {
                log.error("The inventory-service is interrupted while sleeping, should wait: " + DELAY_TIME);
            }
        }
    }
}
