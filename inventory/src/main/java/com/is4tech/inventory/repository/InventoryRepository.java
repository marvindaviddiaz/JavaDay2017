package com.is4tech.inventory.repository;


import com.is4tech.inventory.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	List<Inventory> findByEventAndAvailableIsGreaterThan(String event, Integer available);

	Inventory findByEventAndCategory(String event, String category);

}
