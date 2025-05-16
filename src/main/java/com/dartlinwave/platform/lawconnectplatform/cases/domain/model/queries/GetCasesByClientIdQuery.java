package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries;

/**
 * Query to retrieve all cases associated with a specific client.
 *
 * @param clientId the unique identifier of the client whose cases are to be retrieved
 */
public record GetCasesByClientIdQuery(Long clientId) {
}