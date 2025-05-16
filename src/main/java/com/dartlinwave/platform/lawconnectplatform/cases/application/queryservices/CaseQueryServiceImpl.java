package com.dartlinwave.platform.lawconnectplatform.cases.application.queryservices;

import org.springframework.stereotype.Service;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates.Case;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.services.CaseQueryService;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries.GetCasesByClientIdQuery;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries.GetCasesBySpecialtyQuery;
import com.dartlinwave.platform.lawconnectplatform.cases.infrastructure.persistence.jpa.repositories.CaseRepository;

import java.util.List;

/**
 * Service implementation for handling queries related to legal cases.
 * <p>
 * Provides methods to retrieve cases by client ID or required specialty.
 * </p>
 */
@Service
public class CaseQueryServiceImpl implements CaseQueryService {

    /**
     * Repository for accessing case data.
     */
    private final CaseRepository caseRepository;

    /**
     * Constructs a new {@code CaseQueryServiceImpl} with the given {@link CaseRepository}.
     *
     * @param caseRepository the repository for case persistence
     */
    public CaseQueryServiceImpl(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    /**
     * Retrieves a list of cases associated with a specific client.
     *
     * @param query the query containing the client ID
     * @return a list of cases for the given client
     */
    @Override
    public List<Case> handle(GetCasesByClientIdQuery query) {
        return caseRepository.findByClientId(query.clientId());
    }

    /**
     * Retrieves a list of cases that require a specific specialty.
     *
     * @param query the query containing the required specialty
     * @return a list of cases matching the specialty
     */
    @Override
    public List<Case> handle(GetCasesBySpecialtyQuery query) {
        return caseRepository.findCasesByRequiredSpecialty(query.specialty());
    }
}