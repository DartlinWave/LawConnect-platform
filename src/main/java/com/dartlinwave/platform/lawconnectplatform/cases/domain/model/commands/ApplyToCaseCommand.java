package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands;

/**
 * Command to apply a lawyer to a case.
 * Contains the identifiers for the case and the lawyer applying.
 *
 * @param caseId   the unique identifier of the case
 * @param lawyerId the unique identifier of the lawyer applying
 */
public record ApplyToCaseCommand(Long caseId, Long lawyerId) {
}