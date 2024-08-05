package dev.warehouseService.controller;

import dev.warehouseService.dto.WarehouseDTO;
import dev.warehouseService.entity.Warehouse;
import dev.warehouseService.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public List<WarehouseDTO> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public WarehouseDTO getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id);
    }

    @PostMapping
    public WarehouseDTO createWarehouse(@RequestBody WarehouseDTO warehouse) {
        return warehouseService.createWarehouse(warehouse);
    }

    @PutMapping("/{id}")
    public WarehouseDTO updateWarehouse(@PathVariable Long id, @RequestBody WarehouseDTO warehouseDetails) {
        return warehouseService.updateWarehouse(id, warehouseDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
    }
}