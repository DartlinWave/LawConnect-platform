package com.dartlinwave.platform.lawconnectplatform.cases.application.commandservices;

import org.springframework.stereotype.Service;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates.Case;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.entities.Applicant;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.services.CaseCommandService;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.CreateCaseCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.ApplyToCaseCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.RejectLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.AcceptLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.infrastructure.persistence.jpa.repositories.CaseRepository;

import java.util.Optional;

/**
 * Service implementation for handling commands related to legal cases.
 * Provides methods to create cases, apply to cases, and accept or reject lawyers.
 */
@Service
public class CaseCommandServiceImpl implements CaseCommandService {

    private final CaseRepository caseRepository;

    /**
     * Constructs a new CaseCommandServiceImpl with the given repository.
     *
     * @param caseRepository the repository for accessing case data
     */
    public CaseCommandServiceImpl(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    /**
     * Handles the creation of a new case.
     * Throws an exception if a case with the same title already exists.
     *
     * @param command the command containing the details for the new case
     * @return an {@link Optional} containing the created case, or empty if creation failed
     * @throws IllegalArgumentException if a case with the given title already exists
     */
    @Override
    public Optional<Case> handle(CreateCaseCommand command) {
        if (caseRepository.existsByTitle(command.title()))
            throw new IllegalArgumentException("Case with this title already exists");

        Case newCase = Case.create(
                command.clientId(),
                command.title(),
                command.description(),
                command.specialty()
        );

        return Optional.of(caseRepository.save(newCase));
    }

    /**
     * Handles the application of a lawyer to a case.
     * Adds a new applicant to the case.
     *
     * @param command the command containing the case and lawyer details
     * @throws IllegalArgumentException if the case is not found
     */
    @Override
    public void handle(ApplyToCaseCommand command) {
        Case caseEntity = caseRepository.findById(command.caseId())
                .orElseThrow(() -> new IllegalArgumentException("Case not found"));

        Applicant applicant = Applicant.create(command.lawyerId());
        caseEntity.addApplicant(applicant);

        caseRepository.save(caseEntity);
    }

    /**
     * Handles the acceptance of a lawyer for a case.
     * Updates the applicant's status to accepted.
     *
     * @param command the command containing the case and lawyer details
     * @throws IllegalArgumentException if the case is not found
     */
    @Override
    public void handle(AcceptLawyerCommand command) {
        Case caseEntity = caseRepository.findById(command.caseId())
                .orElseThrow(() -> new IllegalArgumentException("Case not found"));

        caseEntity.acceptApplicant(command.lawyerId());
        caseRepository.save(caseEntity);
    }

    /**
     * Handles the rejection of a lawyer for a case.
     * Updates the applicant's status to rejected.
     *
     * @param command the command containing the case and lawyer details
     * @throws IllegalArgumentException if the case is not found
     */
    @Override
    public void handle(RejectLawyerCommand command) {
        Case caseEntity = caseRepository.findById(command.caseId())
                .orElseThrow(() -> new IllegalArgumentException("Case not found"));

        caseEntity.rejectApplicant(command.lawyerId());
        caseRepository.save(caseEntity);
    }
}