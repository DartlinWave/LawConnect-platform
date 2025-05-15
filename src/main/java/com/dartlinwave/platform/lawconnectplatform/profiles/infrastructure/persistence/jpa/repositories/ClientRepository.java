package com.dartlinwave.platform.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.aggregates.Client;

import java.util.Optional;

/**
 * Repository interface for managing {@link Client} entities.
 * <p>
 * Provides methods for accessing and querying client data in the database.
 * </p>
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Finds a client by the associated user ID.
     *
     * @param userId the user ID to search for
     * @return an {@link Optional} containing the found {@link Client}, or empty if not found
     */
    Optional<Client> findClientByUserId(Long userId);

    /**
     * Checks if a client exists with the given DNI.
     *
     * @param dni the DNI to check
     * @return {@code true} if a client with the given DNI exists, {@code false} otherwise
     */
    boolean existsByDni(String dni);

    /**
     * Checks if a client exists with the given user ID.
     *
     * @param userId the user ID to check
     * @return {@code true} if a client with the given user ID exists, {@code false} otherwise
     */
    boolean existsByUserId(Long userId);
}