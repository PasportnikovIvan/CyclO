package dev.warehouseService.controller;

import dev.warehouseService.dto.WarehouseDTO;
import dev.warehouseService.entity.Warehouse;
import dev.warehouseService.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing warehouse records.
 * Provides endpoints for creating, updating, retrieving, and deleting warehouses,
 * as well as updating inventory.
 */
@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    /**
     * Retrieves all warehouse records.
     *
     * @return a list of WarehouseDTO objects representing all warehouse records
     */
    @GetMapping
    public List<WarehouseDTO> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    /**
     * Retrieves a warehouse record by its ID.
     *
     * @param id the ID of the warehouse record to retrieve
     * @return the WarehouseDTO representing the retrieved warehouse record
     */
    @GetMapping("/{id}")
    public WarehouseDTO getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id);
    }

    /**
     * Creates a new warehouse record.
     *
     * @param warehouse the WarehouseDTO representing the warehouse record to create
     * @return the WarehouseDTO representing the created warehouse record
     */
    @PostMapping
    public WarehouseDTO createWarehouse(@RequestBody WarehouseDTO warehouse) {
        return warehouseService.createWarehouse(warehouse);
    }

    /**
     * Updates an existing warehouse record.
     *
     * @param id the ID of the warehouse record to update
     * @param warehouseDetails the WarehouseDTO containing the updated warehouse details
     * @return the WarehouseDTO representing the updated warehouse record
     */
    @PutMapping("/{id}")
    public WarehouseDTO updateWarehouse(@PathVariable Long id, @RequestBody WarehouseDTO warehouseDetails) {
        return warehouseService.updateWarehouse(id, warehouseDetails);
    }

    /**
     * Deletes a warehouse record by its ID.
     *
     * @param id the ID of the warehouse record to delete
     */
    @DeleteMapping("/{id}")
    public void deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
    }

    /**
     * Updates the inventory for a specific part in the warehouse.
     *
     * @param partId the ID of the part to update
     * @param quantity the new quantity of the part
     * @return a ResponseEntity with no content
     */
    @PostMapping("/{id}")
    public ResponseEntity<Void> updateInventory(@RequestParam Long partId, @RequestParam int quantity) {
        warehouseService.updateInventory(partId, quantity);
        return ResponseEntity.noContent().build();
    }
}