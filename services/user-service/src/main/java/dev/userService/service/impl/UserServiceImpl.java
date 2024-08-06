package dev.userService.service.impl;

import dev.userService.dto.UserDTO;
import dev.userService.entity.User;
import dev.userService.exception.ResourceNotFoundException;
import dev.userService.mapper.UserMapper;
import dev.userService.repository.UserRepository;
import dev.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Implementation of the UserService interface. Provides methods for managing users.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    /**
     * Constructs a UserServiceImpl with the specified UserRepository and UserMapper.
     *
     * @param userRepository the repository for user entities
     * @param userMapper     the mapper for converting between User entities and DTOs
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    /**
     * Retrieves all users.
     *
     * @return a list of UserDTOs
     */
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }
    /**
     * Retrieves a user by its ID.
     *
     * @param userId the ID of the user
     * @return the UserDTO
     * @throws ResourceNotFoundException if the user is not found
     */
    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        return userMapper.userToUserDTO(user);
    }
    /**
     * Creates a new user.
     *
     * @param userDTO the user details
     * @return the created UserDTO
     */
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }
    /**
     * Deletes a user by its ID.
     *
     * @param userId the ID of the user to delete
     */
    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        userRepository.delete(user);
    }
}
