package com.dartlinwave.platform.lawconnectplatform.profiles.domain.services;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.aggregates.Client;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.queries.GetClientByUserIdQuery;

import java.util.Optional;

/**
 * Service interface for handling client-related queries.
 * <p>
 * Provides operations to retrieve client profile information from the system.
 * </p>
 */
public interface ClientQueryService {

    /**
     * Handles the retrieval of a client profile based on the provided query.
     *
     * @param query the query containing the user ID for which the client profile is to be retrieved
     * @return an {@link Optional} containing the {@link Client} if found, or empty if not found
     */
    Optional<Client> handle(GetClientByUserIdQuery query);
}