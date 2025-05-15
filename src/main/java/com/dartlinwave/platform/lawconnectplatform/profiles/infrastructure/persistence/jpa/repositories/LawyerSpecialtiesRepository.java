package com.dartlinwave.platform.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.valueobjects.LawyerSpecialties;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.entities.LawyerSpecialty;

import java.util.Optional;

/**
 * Repository interface for managing {@link LawyerSpecialty} entities.
 * <p>
 * Provides methods for accessing and querying lawyer specialty data in the database.
 * </p>
 */
@Repository
public interface LawyerSpecialtiesRepository extends JpaRepository<LawyerSpecialty, Long> {

    /**
     * Finds a lawyer specialty by its name.
     *
     * @param name the {@link LawyerSpecialty} name to search for
     * @return an {@link Optional} containing the found {@link LawyerSpecialty}, or empty if not found
     */
    Optional<LawyerSpecialty> findByName(LawyerSpecialties name);

    /**
     * Checks if a lawyer specialty exists with the given name.
     *
     * @param name the {@link LawyerSpecialty} name to check
     * @return {@code true} if a lawyer specialty with the given name exists, {@code false} otherwise
     */
    boolean existsByName(LawyerSpecialties name);
}