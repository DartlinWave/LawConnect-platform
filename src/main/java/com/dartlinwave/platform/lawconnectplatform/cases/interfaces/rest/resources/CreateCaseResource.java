package com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.resources;

import java.util.Set;

/**
 * Resource representation for creating a new legal case via the REST API.
 * <p>
 * Encapsulates the data required to create a case, including client information,
 * case details, required specialty, status, and applicant identifiers.
 * </p>
 *
 * @param clientId      the unique identifier of the client creating the case
 * @param title         the title of the case
 * @param description   the detailed description of the case
 * @param requiredSpecialty the specialty required for the case
 * @param status        the initial status of the case
 * @param applicantsIds the set of applicant identifiers for the case
 */
public record CreateCaseResource(
        Long clientId,
        String title,
        String description,
        String requiredSpecialty,
        String status,
        Set<String> applicantsIds
) {
}