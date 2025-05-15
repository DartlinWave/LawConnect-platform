package com.dartlinwave.platform.lawconnectplatform.iam.domain.services;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.aggregates.User;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.queries.GetUserByIdQuery;

import java.util.Optional;

/**
 * Defines operations for querying user information.
 */
public interface UserQueryService {

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param query the query containing the user ID
     * @return an {@link Optional} containing the found {@link User}, or empty if not found
     */
    Optional<User> handle(GetUserByIdQuery query);
}