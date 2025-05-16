package com.dartlinwave.platform.lawconnectplatform.tracking.domain.services;

import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.aggregates.Status;
import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.queries.GetStatusByCaseIdQuery;

import java.util.List;

public interface StatusQueryService {
    List<Status> handle(GetStatusByCaseIdQuery query);
}
