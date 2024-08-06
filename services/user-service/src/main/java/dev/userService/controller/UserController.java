package dev.userService.controller;

import dev.userService.dto.UserDTO;
import dev.userService.entity.User;
import dev.userService.service.UserService;
import dev.userService.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * REST controller for managing user-related operations.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * Gets a list of all users.
     *
     * @return a list of {@link UserDTO}
     */
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
    /**
     * Gets a user by its ID.
     *
     * @param id the ID of the user
     * @return the {@link UserDTO}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
    /**
     * Creates a new user.
     *
     * @param userDTO the user details
     * @return the created {@link UserDTO}
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }
    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
