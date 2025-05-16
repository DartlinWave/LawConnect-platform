package com.dartlinwave.platform.lawconnectplatform.cases.domain.services;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates.Case;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.CreateCaseCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.ApplyToCaseCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.AcceptLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.RejectLawyerCommand;

import java.util.Optional;

/**
 * Service interface for handling commands related to legal cases.
 */
public interface CaseCommandService {

    /**
     * Handles the creation of a new case.
     *
     * @param command the command containing the details for the new case
     * @return an {@link Optional} containing the created case, or empty if creation failed
     */
    Optional<Case> handle(CreateCaseCommand command);

    /**
     * Handles the application of a lawyer to a case.
     *
     * @param command the command containing the case and lawyer details
     */
    void handle(ApplyToCaseCommand command);

    /**
     * Handles the acceptance of a lawyer for a case.
     *
     * @param command the command containing the case and lawyer details
     */
    void handle(AcceptLawyerCommand command);

    /**
     * Handles the rejection of a lawyer for a case.
     *
     * @param command the command containing the case and lawyer details
     */
    void handle(RejectLawyerCommand command);
}