package dev.warehouseService.mapper;

import dev.warehouseService.dto.WarehouseDTO;
import dev.warehouseService.entity.Warehouse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapper interface for converting between Warehouse entities and WarehouseDTO objects.
 * Uses MapStruct for automatic mapping.
 */
@Mapper(componentModel = "spring")
@Component
public interface WarehouseMapper {

    /**
     * Converts a Warehouse entity to a WarehouseDTO.
     *
     * @param warehouse the Warehouse entity to be converted
     * @return the converted WarehouseDTO
     */
    WarehouseDTO warehouseToWarehouseDTO(Warehouse warehouse);

    /**
     * Converts a WarehouseDTO to a Warehouse entity.
     *
     * @param warehouseDTO the WarehouseDTO to be converted
     * @return the converted Warehouse entity
     */
    Warehouse warehouseDTOToWarehouse(WarehouseDTO warehouseDTO);
}
