package dev.bikeService.mapper;

import dev.bikeService.dto.BikeDTO;
import dev.bikeService.entity.Bike;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
/**
 * Mapper for converting between Bike entity and BikeDTO.
 */
@Mapper(componentModel = "spring")
@Component
public interface BikeMapper {

    /**
     * Converts a Bike entity to a BikeDTO.
     *
     * @param bike the bike entity
     * @return the bike DTO
     */
    BikeDTO bikeToBikeDTO(Bike bike);
    /**
     * Converts a BikeDTO to a Bike entity.
     *
     * @param bikeDTO the bike DTO
     * @return the bike entity
     */
    Bike bikeDTOToBike(BikeDTO bikeDTO);
}