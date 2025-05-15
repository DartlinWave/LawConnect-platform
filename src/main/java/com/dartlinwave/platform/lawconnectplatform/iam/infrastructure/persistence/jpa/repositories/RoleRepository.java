package com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.persistence.jpa.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.entities.Role;

import java.util.Optional;

/**
 * Repository interface for managing {@link Role} entities.
 * <p>
 * Extends {@link JpaRepository} to provide CRUD operations and custom queries
 * for roles in the persistence layer.
 * </p>
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a role by its name.
     *
     * @param name the name of the role to find
     * @return an {@link Optional} containing the found role, or empty if not found
     */
    Optional<Role> findByName(String name);

    /**
     * Checks if a role with the given name exists.
     *
     * @param name the name of the role to check
     * @return {@code true} if a role with the given name exists, {@code false} otherwise
     */
    boolean existsByName(String name);
}