package com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.resources;

/**
 * Resource representation of an applicant for a case.
 *
 * @param lawyerId the unique identifier of the lawyer applying to the case
 * @param status the status of the application (e.g., "pending", "accepted", "rejected")
 * @param appliedAt the date and time when the application was submitted
 */
public record ApplicantResource(Long lawyerId, String status, String appliedAt) {
}