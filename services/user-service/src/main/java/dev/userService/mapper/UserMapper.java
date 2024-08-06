package dev.userService.mapper;

import dev.userService.dto.UserDTO;
import dev.userService.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
/**
 * Mapper for converting between User entity and UserDTO.
 */
@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user the user entity
     * @return the user DTO
     */
    UserDTO userToUserDTO(User user);
    /**
     * Converts a UserDTO to a User entity.
     *
     * @param userDTO the user DTO
     * @return the user entity
     */
    User userDTOToUser(UserDTO userDTO);
}

