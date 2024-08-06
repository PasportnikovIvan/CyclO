package dev.rentalService.mapper;

import dev.rentalService.dto.RentalDTO;
import dev.rentalService.entity.RentalRecord;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface RentalMapper {
    RentalDTO rentalToRentalDTO(RentalRecord rentalRecord);
    RentalRecord rentalDTOToRental(RentalDTO rentalDTO);
}

