package com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.persistence.jpa.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.aggregates.User;

import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities.
 * <p>
 * Extends {@link JpaRepository} to provide CRUD operations and custom queries
 * for users in the persistence layer.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to find
     * @return an {@link Optional} containing the found user, or empty if not found
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a user with the given username exists.
     *
     * @param username the username to check
     * @return {@code true} if a user with the given username exists, {@code false} otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Checks if a user with the given ID exists.
     *
     * @param userId the ID of the user to check
     * @return {@code true} if a user with the given ID exists, {@code false} otherwise
     */
    boolean existsById(Long userId);
}