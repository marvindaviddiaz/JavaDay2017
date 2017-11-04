package com.is4tech.inventory;

import com.is4tech.inventory.domain.Inventory;
import com.is4tech.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;

@EnableDiscoveryClient
@SpringBootApplication
public class InventoryApplication implements CommandLineRunner {

    @Autowired
    InventoryRepository inventoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    /**
     * DEFAULT DATA
     */
    @Override
    public void run(String... strings) throws Exception {
        Inventory[] invs = {
                new Inventory("E101", "GRA", 100.00, 1_000),
                new Inventory("E101", "PREF", 200.00, 500),
                new Inventory("E101", "VIP", 350.00, 250),
                new Inventory("E101", "ORO", 600.00,  100),
                new Inventory("E101", "PLA", 800.00, 100),
                new Inventory("E101", "DIA", 1200.00, 100)};
        Arrays.asList(invs).forEach(inventory -> inventoryRepository.save(inventory));
    }
}
