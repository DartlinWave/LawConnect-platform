package com.dartlinwave.platform.lawconnectplatform.cases.application.commandservices;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates.Case;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.AcceptLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.ApplyToCaseCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.CreateCaseCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.RejectLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.entities.Applicant;
import com.dartlinwave.platform.lawconnectplatform.cases.infrastructure.persistence.jpa.repositories.CaseRepository;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.services.CaseCommandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CaseCommandServiceImpl implements CaseCommandService {

    private final CaseRepository caseRepository;

    public CaseCommandServiceImpl(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Override
    @Transactional
    public Case handleCreateCase(CreateCaseCommand command) {
        Case newCase = Case.create(
                command.getClientId(),
                command.getTitle(),
                command.getDescription(),
                command.getSpecialty()
        );

        return caseRepository.save(newCase);
    }

    @Override
    @Transactional
    public void handleApplyToCase(ApplyToCaseCommand command) {
        Case caseEntity = caseRepository.findById(command.getCaseId())
                .orElseThrow(() -> new IllegalArgumentException("Case not found"));

        Applicant applicant = Applicant.create(command.getLawyerId());
        caseEntity.addApplicant(applicant);

        caseRepository.save(caseEntity);
    }

    @Override
    @Transactional
    public void handleAcceptLawyer(AcceptLawyerCommand command) {
        Case caseEntity = caseRepository.findById(command.getCaseId())
                .orElseThrow(() -> new IllegalArgumentException("Case not found"));

        caseEntity.acceptLawyer(command.getLawyerId());
        caseRepository.save(caseEntity);
    }

    @Override
    @Transactional
    public void handleRejectLawyer(RejectLawyerCommand command) {
        Case caseEntity = caseRepository.findById(command.getCaseId())
                .orElseThrow(() -> new IllegalArgumentException("Case not found"));

        caseEntity.rejectLawyer(command.getLawyerId());
        caseRepository.save(caseEntity);
    }
}