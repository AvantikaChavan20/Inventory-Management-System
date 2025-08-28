package com.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.model.Inventory;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

	List<Inventory> findByLocationIgnoreCase(String location);

}
