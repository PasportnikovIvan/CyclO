package dev.rentalService.repository;

import dev.rentalService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUserIdIs(Long userId);
}
