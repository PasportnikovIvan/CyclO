package dev.warehouseService.service;

import dev.warehouseService.entity.BikePart;
import dev.warehouseService.entity.Warehouse;
import dev.warehouseService.exception.ResourceNotFoundException;
import dev.warehouseService.kafka.MessageProducer;
import dev.warehouseService.repository.BikePartRepository;
import dev.warehouseService.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private BikePartRepository bikePartRepository;
    @Autowired
    private MessageProducer messageProducer;

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

    /**
     * Updates the inventory for a given BikePart.
     *
     * @param partId   the ID of the BikePart to update
     * @param quantity the new quantity to set
     */
    @Transactional
    public void updateInventory(Long partId, int quantity) {
        // Fetch the BikePart from the database
        BikePart bikePart = bikePartRepository.findById(partId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid part ID: " + partId));

        bikePartRepository.save(bikePart);

        // Construct a message detailing the inventory update
        String inventoryMessage = String.format("Inventory updated for BikePart: %d, new quantity: %d", partId, quantity);

        // Send the message to the 'warehouse-topic' Kafka topic
        messageProducer.sendMessage("warehouse-topic", inventoryMessage);

        // Log the action or perform additional business logic if necessary
        System.out.println(inventoryMessage);
    }
}

