package com.dartlinwave.platform.lawconnectplatform.cases.infrastructure.persistence.jpa.repositories;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates.Case;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing and managing {@link Case} entities in the database.
 * Extends {@link JpaRepository} to provide standard CRUD operations.
 */
@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {

    /**
     * Retrieves a list of cases associated with a specific client ID.
     *
     * @param clientId the unique identifier of the client
     * @return a list of cases for the given client
     */
    List<Case> findByClientId(Long clientId);

    /**
     * Retrieves a list of cases that require a specific specialty.
     *
     * @param specialty the required specialty for the cases
     * @return a list of cases matching the given specialty
     */
    List<Case> findCasesByRequiredSpecialty(Specialty specialty);


   /**
     * Checks if a case with the specified title exists in the database.
     *
     * @param title the title of the case to check for existence
     * @return true if a case with the given title exists, false otherwise
     */
    boolean existsByTitle(String title);
}