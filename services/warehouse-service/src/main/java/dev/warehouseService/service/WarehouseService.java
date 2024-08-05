package dev.warehouseService.service;

import dev.warehouseService.dto.WarehouseDTO;
import dev.warehouseService.entity.Warehouse;
import dev.warehouseService.entity.BikePart;

import java.util.List;

public interface WarehouseService {
    List<WarehouseDTO> getAllWarehouses();
    WarehouseDTO getWarehouseById(Long id);
    WarehouseDTO createWarehouse(WarehouseDTO warehouse);
    WarehouseDTO updateWarehouse(Long id, WarehouseDTO warehouseDetails);
    void deleteWarehouse(Long id);
    void updateInventory(Long partId, int quantity);
}
