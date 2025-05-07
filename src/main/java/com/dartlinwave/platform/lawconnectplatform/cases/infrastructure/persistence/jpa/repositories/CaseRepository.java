package com.dartlinwave.platform.lawconnectplatform.cases.infrastructure.persistence.jpa.repositories;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates.Case;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository  // Asegúrate de que esta anotación esté presente
public interface CaseRepository extends JpaRepository<Case, Long> {
    Case save(Case caseEntity);
    Optional<Case> findById(Long id);
    List<Case> findByClientId(UUID clientId);
    List<Case> findBySpecialtyRequired(Specialty specialty);
}