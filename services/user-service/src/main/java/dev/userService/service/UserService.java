package dev.userService.service;

import dev.userService.dto.UserDTO;
import java.util.List;
/**
 * Service interface for managing users.
 */
public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long userId);
    UserDTO createUser(UserDTO userDTO);
    void deleteUser(Long userId);
}

