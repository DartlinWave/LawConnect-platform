package com.dartlinwave.platform.lawconnectplatform.iam.domain.model.queries;

/**
 * Query to retrieve a user by their unique identifier.
 *
 * @param userId the unique identifier of the user to be retrieved
 */
public record GetUserByIdQuery(Long userId) {
}