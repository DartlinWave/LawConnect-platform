package com.dartlinwave.platform.lawconnectplatform.tracking.application.queryservices;

import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.aggregates.Status;
import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.queries.GetStatusByCaseIdQuery;
import com.dartlinwave.platform.lawconnectplatform.tracking.domain.services.StatusQueryService;
import com.dartlinwave.platform.lawconnectplatform.tracking.infrastructure.persistence.jpa.repositories.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatusQueryServiceImpl implements StatusQueryService {
    private final StatusRepository statusRepository;

    public StatusQueryServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }
    @Override
    public List<Status> handle(GetStatusByCaseIdQuery query) {
        return statusRepository.findStatusesByLegalCaseId(query.legalCaseId());
    }
}
