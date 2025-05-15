package com.dartlinwave.platform.lawconnectplatform.profiles.domain.services;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.queries.GetLawyerByUserIdQuery;

import java.util.Optional;

/**
 * Service interface for handling lawyer-related queries.
 * <p>
 * Provides operations to retrieve lawyer profile information from the system.
 * </p>
 */
public interface LawyerQueryService {

    /**
     * Handles the retrieval of a lawyer profile based on the provided query.
     *
     * @param query the query containing the user ID for which the lawyer profile is to be retrieved
     * @return an {@link Optional} containing the {@link Lawyer} if found, or empty if not found
     */
    Optional<Lawyer> handle(GetLawyerByUserIdQuery query);
}