package com.dartlinwave.platform.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;

import java.util.Optional;

/**
 * Repository interface for managing {@link Lawyer} entities.
 * <p>
 * Provides methods for accessing and querying lawyer data in the database.
 * </p>
 */
@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

    /**
     * Finds a lawyer by the associated user ID.
     *
     * @param userId the user ID to search for
     * @return an {@link Optional} containing the found {@link Lawyer}, or empty if not found
     */
    Optional<Lawyer> findLawyerByUserId(Long userId);

    /**
     * Checks if a lawyer exists with the given DNI.
     *
     * @param dni the DNI to check
     * @return {@code true} if a lawyer with the given DNI exists, {@code false} otherwise
     */
    boolean existsByDni(String dni);

    /**
     * Checks if a lawyer exists with the given user ID.
     *
     * @param userId the user ID to check
     * @return {@code true} if a lawyer with the given user ID exists, {@code false} otherwise
     */
    boolean existsByUserId(Long userId);
}