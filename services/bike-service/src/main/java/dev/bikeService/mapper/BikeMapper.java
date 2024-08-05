package dev.bikeService.mapper;

import dev.bikeService.dto.BikeDTO;
import dev.bikeService.entity.Bike;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BikeMapper {
    BikeDTO bikeToBikeDTO(Bike bike);
    Bike bikeDTOToBike(BikeDTO bikeDTO);
}