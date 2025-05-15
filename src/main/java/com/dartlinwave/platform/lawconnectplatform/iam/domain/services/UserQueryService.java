package com.dartlinwave.platform.lawconnectplatform.iam.domain.services;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.queries.GetUserByIdQuery;

/**
 * Service interface for handling user-related queries.
 * Provides operations to retrieve user information from the system.
 */
public interface UserQueryService {

    /**
     * Handles the retrieval of a user by their unique identifier.
     *
     * @param query the query containing the user ID to search for
     */
    void handle(GetUserByIdQuery query);
}