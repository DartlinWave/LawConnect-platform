package com.dartlinwave.platform.lawconnectplatform.tracking.infrastructure.persistence.jpa.repositories;

import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.aggregates.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {
    boolean existsByLegalCaseId(long legalCaseId);
    List<Status> findByLegalCaseId(long legalCaseId);
}
