package com.is4tech.inventory.controller;

import com.is4tech.inventory.component.InventoryComponent;
import com.is4tech.inventory.domain.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/inventory")
@RestController
public class InventoryController {

    private final InventoryComponent inventoryComponent;

    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    public InventoryController(InventoryComponent inventoryComponent) {
        this.inventoryComponent = inventoryComponent;
    }

    @GetMapping
    List<Inventory> search(@RequestParam("event") String event){
        logger.info("Input : "+ event);
        return inventoryComponent.search(event);
    }
}
