package com.is4tech.inventory.component;

import com.is4tech.inventory.domain.Inventory;
import com.is4tech.inventory.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryComponent {

    private static final Logger logger = LoggerFactory.getLogger(InventoryComponent.class);
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryComponent(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> search(String event) {
        return inventoryRepository.findByEventAndAvailableIsGreaterThan(event, 0);
    }

    public void updateInventory(String event, String category, Integer quantity) {
        logger.info("Updating Inventory: {} {}", event , category);
        Inventory i = this.inventoryRepository.findByEventAndCategory(event, category);
        Integer available = i.getAvailable() - quantity;
        if(available < 0) {
            // TODO: booking cancel - async
            available = 0;
        }
        i.setAvailable(available);
        this.inventoryRepository.save(i);
    }

}
