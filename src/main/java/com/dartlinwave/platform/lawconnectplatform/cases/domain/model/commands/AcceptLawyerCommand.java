package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands;

/**
 * Command to accept a lawyer's application to a case.
 * Contains the identifiers for the case and the lawyer to be accepted.
 *
 * @param caseId   the unique identifier of the case
 * @param lawyerId the unique identifier of the lawyer to accept
 */
public record AcceptLawyerCommand(Long caseId, Long lawyerId) {
}