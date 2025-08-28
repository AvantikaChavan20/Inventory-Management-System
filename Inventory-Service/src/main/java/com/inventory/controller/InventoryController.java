
package com.inventory.controller;

import com.inventory.dto.InventoryDto;
import com.inventory.exception.InventoryIdNotFoundException;
import com.inventory.exception.NoInventoryFoundException;
import com.inventory.model.Inventory;
import com.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Get all inventory items
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() throws NoInventoryFoundException {
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }

    // Get inventory by ID
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) throws InventoryIdNotFoundException {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    // Create a new inventory
    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody InventoryDto inventoryDto) {
        return ResponseEntity.ok(inventoryService.createInventory(inventoryDto));
    }

    // Update an existing inventory
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody InventoryDto inventoryDto) throws InventoryIdNotFoundException {
        return ResponseEntity.ok(inventoryService.updateInventory(id, inventoryDto));
    }

    // Delete an inventory
    @DeleteMapping("/{id}")
    public ResponseEntity<Inventory> deleteInventory(@PathVariable Long id) throws InventoryIdNotFoundException {
        return ResponseEntity.ok(inventoryService.deleteInventory(id));
    }
    
    //Get inventories by location
    @GetMapping("inventoriesByLocation/{location}")
	public ResponseEntity<List<Inventory>> getInventoriesByLocation(@PathVariable String location) throws NoInventoryFoundException{
		return ResponseEntity.ok(inventoryService.getInventoriesByLocation(location));
	}
    
}
