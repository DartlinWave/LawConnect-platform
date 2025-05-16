package com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.resources;

import java.util.Set;

/**
 * Resource representation of a legal case for REST API responses.
 * <p>
 * Encapsulates case data, including applicants, to be sent to API consumers.
 * </p>
 *
 * @param caseId           the unique identifier of the case
 * @param clientId         the unique identifier of the client who created the case
 * @param title            the title of the case
 * @param description      the detailed description of the case
 * @param requiredSpecialty the specialty required for the case
 * @param status           the current status of the case (e.g., "open", "closed")
 * @param applicants    the set of applicants for the case
 * @param createdAt        the date and time when the case was created
 */
public record CaseResource(
        Long caseId,
        Long clientId,
        String title,
        String description,
        String requiredSpecialty,
        String status,
        Set<ApplicantResource> applicants,
        String createdAt
) {
}