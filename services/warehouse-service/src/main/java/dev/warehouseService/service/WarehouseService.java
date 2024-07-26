package dev.warehouseService.service;

import dev.shared.CustomLoggerFactory;
import dev.warehouseService.entity.BikePart;
import dev.warehouseService.entity.Warehouse;
import dev.warehouseService.exception.ResourceNotFoundException;
import dev.warehouseService.kafka.MessageProducer;
import dev.warehouseService.repository.BikePartRepository;
import dev.warehouseService.repository.WarehouseRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final BikePartRepository bikePartRepository;
    private final MessageProducer messageProducer;

    private static final Logger logger = CustomLoggerFactory.getLogger(WarehouseService.class);

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository,
                            BikePartRepository bikePartRepository,
                            MessageProducer messageProducer) {
        this.warehouseRepository = warehouseRepository;
        this.bikePartRepository = bikePartRepository;
        this.messageProducer = messageProducer;
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id " + id));
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Long id, Warehouse warehouseDetails) {
        Warehouse warehouse = getWarehouseById(id);
        warehouse.setParts(warehouseDetails.getParts());
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long id) {
        Warehouse warehouse = getWarehouseById(id);
        warehouseRepository.delete(warehouse);
    }

    @Transactional
    public void updateInventory(Long partId, int quantity) {
        BikePart bikePart = bikePartRepository.findById(partId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid part ID: " + partId));
        bikePartRepository.save(bikePart);

        String inventoryMessage = String.format("Inventory updated for BikePart: %d, new quantity: %d", partId, quantity);
        messageProducer.sendMessage("warehouse-topic", inventoryMessage);
        logger.info(inventoryMessage);
    }
}
