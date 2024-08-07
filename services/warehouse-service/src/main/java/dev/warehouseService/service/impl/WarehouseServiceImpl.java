package dev.warehouseService.service.impl;

import dev.warehouseService.dto.WarehouseDTO;
import dev.warehouseService.entity.BikePart;
import dev.warehouseService.entity.Warehouse;
import dev.warehouseService.exception.ResourceNotFoundException;
import dev.warehouseService.kafka.MessageProducer;
import dev.warehouseService.mapper.WarehouseMapper;
import dev.warehouseService.repository.BikePartRepository;
import dev.warehouseService.repository.WarehouseRepository;
import dev.warehouseService.service.WarehouseService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import shared.CustomLoggerFactory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing warehouse records.
 * Provides methods for creating, updating, retrieving, and deleting warehouses,
 * as well as updating inventory.
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final BikePartRepository bikePartRepository;
    private final MessageProducer messageProducer;
    private final WarehouseMapper mapper;

    private static final Logger logger = CustomLoggerFactory.getLogger(WarehouseServiceImpl.class);

    /**
     * Constructs a new WarehouseServiceImpl with the specified repositories, message producer, and mapper.
     *
     * @param warehouseRepository the repository for warehouse records
     * @param bikePartRepository the repository for bike parts
     * @param messageProducer the producer for sending messages
     * @param mapper the mapper for converting between entities and DTOs
     */
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository,
                                BikePartRepository bikePartRepository,
                                MessageProducer messageProducer, WarehouseMapper mapper) {
        this.warehouseRepository = warehouseRepository;
        this.bikePartRepository = bikePartRepository;
        this.messageProducer = messageProducer;
        this.mapper = mapper;
    }

    /**
     * Retrieves all warehouse records.
     *
     * @return a list of WarehouseDTO objects representing all warehouse records
     */
    @Override
    public List<WarehouseDTO> getAllWarehouses() {
        var warehouses =  warehouseRepository.findAll();
        return warehouses.stream()
                .map(mapper::warehouseToWarehouseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a warehouse record by its ID.
     *
     * @param id the ID of the warehouse record to retrieve
     * @return the WarehouseDTO representing the retrieved warehouse record
     * @throws ResourceNotFoundException if no warehouse record is found with the given ID
     */
    @Override
    public WarehouseDTO getWarehouseById(Long id) {
        var warehouse =  warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id " + id));
        return mapper.warehouseToWarehouseDTO(warehouse);
    }

    /**
     * Creates a new warehouse record.
     *
     * @param warehouseDTO the WarehouseDTO representing the warehouse record to create
     * @return the WarehouseDTO representing the created warehouse record
     */
    @Override
    public WarehouseDTO createWarehouse(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = mapper.warehouseDTOToWarehouse(warehouseDTO);
        Warehouse createdWarehouse = warehouseRepository.save(warehouse);
        return mapper.warehouseToWarehouseDTO(createdWarehouse);
    }

    /**
     * Updates an existing warehouse record.
     *
     * @param id the ID of the warehouse record to update
     * @param warehouseDTO the WarehouseDTO containing the updated warehouse details
     * @return the WarehouseDTO representing the updated warehouse record
     */
    @Override
    public WarehouseDTO updateWarehouse(Long id, WarehouseDTO warehouseDTO) {
        Warehouse warehouse = warehouseRepository.getById(id);
        warehouse.setParts(warehouseDTO.getParts());
        var updatedWarehouse = warehouseRepository.save(warehouse);
        return mapper.warehouseToWarehouseDTO(updatedWarehouse);
    }

    /**
     * Deletes a warehouse record by its ID.
     *
     * @param id the ID of the warehouse record to delete
     */
    @Override
    public void deleteWarehouse(Long id) {
        Warehouse warehouse = warehouseRepository.getById(id);
        warehouseRepository.delete(warehouse);
    }

    /**
     * Updates the inventory for a specific part in the warehouse.
     *
     * @param partId the ID of the part to update
     * @param quantity the new quantity of the part
     * @throws IllegalArgumentException if the part ID is invalid
     */
    @Override
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
