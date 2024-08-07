package dev.rentalService.mapper;

import dev.rentalService.dto.RentalDTO;
import dev.rentalService.entity.RentalRecord;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapper interface for converting between RentalRecord entities and RentalDTO objects.
 * Uses MapStruct for automatic mapping.
 */
@Mapper(componentModel = "spring")
@Component
public interface RentalMapper {
    
    /**
     * Converts a RentalRecord entity to a RentalDTO.
     *
     * @param rentalRecord the RentalRecord entity to be converted
     * @return the converted RentalDTO
     */
    RentalDTO rentalToRentalDTO(RentalRecord rentalRecord);

    /**
     * Converts a RentalDTO to a RentalRecord entity.
     *
     * @param rentalDTO the RentalDTO to be converted
     * @return the converted RentalRecord entity
     */
    RentalRecord rentalDTOToRental(RentalDTO rentalDTO);
}

