package com.dartlinwave.platform.lawconnectplatform.cases.application.queryservices;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates.Case;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries.GetCaseDetailsQuery;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries.GetCasesByClientQuery;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries.GetCasesBySpecialtyQuery;
import com.dartlinwave.platform.lawconnectplatform.cases.infrastructure.persistence.jpa.repositories.CaseRepository;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.services.CaseQueryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CaseQueryServiceImpl implements CaseQueryService {

    private final CaseRepository caseRepository;

    public CaseQueryServiceImpl(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Case> handleGetCasesBySpecialty(GetCasesBySpecialtyQuery query) {
        return caseRepository.findBySpecialtyRequired(query.getSpecialty());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Case> handleGetCaseDetails(GetCaseDetailsQuery query) {
        return caseRepository.findById(query.getCaseId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Case> handleGetCasesByClient(GetCasesByClientQuery query) {
        return caseRepository.findByClientId(query.getClientId());
    }
}