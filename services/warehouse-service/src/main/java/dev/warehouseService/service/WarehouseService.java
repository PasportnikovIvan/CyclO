package dev.warehouseService.service;

import dev.warehouseService.dto.WarehouseDTO;
import dev.warehouseService.entity.Warehouse;
import dev.warehouseService.entity.BikePart;

import java.util.List;

/**
 * Service interface for managing warehouse records.
 * Provides methods for creating, updating, retrieving, and deleting warehouses,
 * as well as updating inventory.
 */
public interface WarehouseService {

    /**
     * Retrieves all warehouse records.
     *
     * @return a list of WarehouseDTO objects representing all warehouse records
     */
    List<WarehouseDTO> getAllWarehouses();

    /**
     * Retrieves a warehouse record by its ID.
     *
     * @param id the ID of the warehouse record to retrieve
     * @return the WarehouseDTO representing the retrieved warehouse record
     */
    WarehouseDTO getWarehouseById(Long id);

    /**
     * Creates a new warehouse record.
     *
     * @param warehouse the WarehouseDTO representing the warehouse record to create
     * @return the WarehouseDTO representing the created warehouse record
     */
    WarehouseDTO createWarehouse(WarehouseDTO warehouse);

    /**
     * Updates an existing warehouse record.
     *
     * @param id the ID of the warehouse record to update
     * @param warehouseDetails the WarehouseDTO containing the updated warehouse details
     * @return the WarehouseDTO representing the updated warehouse record
     */
    WarehouseDTO updateWarehouse(Long id, WarehouseDTO warehouseDetails);

    /**
     * Deletes a warehouse record by its ID.
     *
     * @param id the ID of the warehouse record to delete
     */
    void deleteWarehouse(Long id);

    /**
     * Updates the inventory for a specific part in the warehouse.
     *
     * @param partId the ID of the part to update
     * @param quantity the new quantity of the part
     */
    void updateInventory(Long partId, int quantity);
}
