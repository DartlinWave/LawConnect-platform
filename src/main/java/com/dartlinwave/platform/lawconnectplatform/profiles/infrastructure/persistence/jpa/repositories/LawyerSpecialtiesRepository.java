package com.dartlinwave.platform.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.valueobjects.LawyerSpecialties;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.entities.Specialty;

import java.util.Optional;

/**
 * Repository interface for managing {@link Specialty} entities.
 * <p>
 * Provides methods for accessing and querying lawyer specialty data in the database.
 * </p>
 */
@Repository
public interface LawyerSpecialtiesRepository extends JpaRepository<Specialty, Long> {

    /**
     * Finds a lawyer specialty by its name.
     *
     * @param name the {@link Specialty} name to search for
     * @return an {@link Optional} containing the found {@link Specialty}, or empty if not found
     */
    Optional<Specialty> findByName(LawyerSpecialties name);

    /**
     * Checks if a lawyer specialty exists with the given name.
     *
     * @param name the {@link Specialty} name to check
     * @return {@code true} if a lawyer specialty with the given name exists, {@code false} otherwise
     */
    boolean existsByName(LawyerSpecialties name);
}