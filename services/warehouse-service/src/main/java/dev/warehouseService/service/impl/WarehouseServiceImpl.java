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

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final BikePartRepository bikePartRepository;
    private final MessageProducer messageProducer;
    private final WarehouseMapper mapper;

    private static final Logger logger = CustomLoggerFactory.getLogger(WarehouseServiceImpl.class);

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository,
                                BikePartRepository bikePartRepository,
                                MessageProducer messageProducer, WarehouseMapper mapper) {
        this.warehouseRepository = warehouseRepository;
        this.bikePartRepository = bikePartRepository;
        this.messageProducer = messageProducer;
        this.mapper = mapper;
    }
    @Override
    public List<WarehouseDTO> getAllWarehouses() {
        var warehouses =  warehouseRepository.findAll();
        return warehouses.stream()
                .map(mapper::warehouseToWarehouseDTO)
                .collect(Collectors.toList());
    }
    @Override
    public WarehouseDTO getWarehouseById(Long id) {
        var warehouse =  warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id " + id));
        return mapper.warehouseToWarehouseDTO(warehouse);
    }

    @Override
    public WarehouseDTO createWarehouse(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = mapper.warehouseDTOToWarehouse(warehouseDTO);
        Warehouse createdWarehouse = warehouseRepository.save(warehouse);
        return mapper.warehouseToWarehouseDTO(createdWarehouse);
    }
    @Override
    public WarehouseDTO updateWarehouse(Long id, WarehouseDTO warehouseDTO) {
        Warehouse warehouse = warehouseRepository.getById(id);
        warehouse.setParts(warehouseDTO.getParts());
        var updatedWarehouse = warehouseRepository.save(warehouse);
        return mapper.warehouseToWarehouseDTO(updatedWarehouse);
    }
    @Override
    public void deleteWarehouse(Long id) {
        Warehouse warehouse = warehouseRepository.getById(id);
        warehouseRepository.delete(warehouse);
    }

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
