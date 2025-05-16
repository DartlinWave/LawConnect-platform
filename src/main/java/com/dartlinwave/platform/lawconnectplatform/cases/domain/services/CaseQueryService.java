package com.dartlinwave.platform.lawconnectplatform.cases.domain.services;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates.Case;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries.GetCasesByClientIdQuery;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries.GetCasesBySpecialtyQuery;

import java.util.List;

/**
 * Service interface for handling queries related to legal cases.
 */
public interface CaseQueryService {

    /**
     * Retrieves a list of cases associated with a specific client.
     *
     * @param query the query containing the client ID
     * @return a list of cases for the given client
     */
    List<Case> handle(GetCasesByClientIdQuery query);

    /**
     * Retrieves a list of cases that require a specific specialty.
     *
     * @param query the query containing the required specialty
     * @return a list of cases matching the specialty
     */
    List<Case> handle(GetCasesBySpecialtyQuery query);
}