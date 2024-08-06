package dev.userService.repository;

import dev.userService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository interface for User entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
