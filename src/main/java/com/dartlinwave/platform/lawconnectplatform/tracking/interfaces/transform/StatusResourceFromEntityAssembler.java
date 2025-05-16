package com.dartlinwave.platform.lawconnectplatform.tracking.interfaces.transform;

import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.aggregates.Status;
import com.dartlinwave.platform.lawconnectplatform.tracking.interfaces.resources.StatusResource;

public class StatusResourceFromEntityAssembler {
    public static StatusResource toResourceFromEntity(Status entity) {
        return new StatusResource(
                entity.getId(),
                entity.getLegalCaseId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
