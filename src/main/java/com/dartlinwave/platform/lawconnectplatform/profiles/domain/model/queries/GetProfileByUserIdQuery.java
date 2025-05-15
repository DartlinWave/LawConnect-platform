package com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.queries;

/**
 * Query to retrieve a profile by the associated user ID.
 * <p>
 * This query is used to fetch either a client or lawyer profile based on the unique user identifier.
 * </p>
 *
 * @param userId the unique identifier of the user whose profile is to be retrieved
 */
public record GetProfileByUserIdQuery(Long userId) {
}