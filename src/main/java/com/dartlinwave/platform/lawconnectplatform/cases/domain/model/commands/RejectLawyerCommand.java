package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands;

/**
 * Command to reject a lawyer's application to a case.
 * Contains the identifiers for the case and the lawyer to be rejected.
 *
 * @param caseId   the unique identifier of the case
 * @param lawyerId the unique identifier of the lawyer to reject
 */
public record RejectLawyerCommand(Long caseId, Long lawyerId) {
}