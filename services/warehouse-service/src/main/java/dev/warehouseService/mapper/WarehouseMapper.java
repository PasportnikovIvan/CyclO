package dev.warehouseService.mapper;

import dev.warehouseService.dto.WarehouseDTO;
import dev.warehouseService.entity.Warehouse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface WarehouseMapper {
    WarehouseDTO warehouseToWarehouseDTO(Warehouse warehouse);
    Warehouse warehouseDTOToWarehouse(WarehouseDTO warehouseDTO);
}
