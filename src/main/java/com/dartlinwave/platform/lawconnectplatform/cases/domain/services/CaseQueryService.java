package com.dartlinwave.platform.lawconnectplatform.cases.domain.services;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates.Case;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries.GetCaseDetailsQuery;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries.GetCasesByClientQuery;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries.GetCasesBySpecialtyQuery;

import java.util.List;
import java.util.Optional;

public interface CaseQueryService {
    List<Case> handleGetCasesBySpecialty(GetCasesBySpecialtyQuery query);
    Optional<Case> handleGetCaseDetails(GetCaseDetailsQuery query);
    List<Case> handleGetCasesByClient(GetCasesByClientQuery query);
}