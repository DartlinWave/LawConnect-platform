package com.dartlinwave.platform.lawconnectplatform.tracking.infrastructure.persistence.jpa.repositories;

import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.entities.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusTypeRepository extends JpaRepository<StatusType, Integer> {

}
