package com.dartlinwave.platform.lawconnectplatform.tracking.application.commandservices;

import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.aggregates.Status;
import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.commands.CreateStatusCommand;
import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.exceptions.LegalCaseAlreadyHaveStatusException;
import com.dartlinwave.platform.lawconnectplatform.tracking.domain.services.StatusCommandService;
import com.dartlinwave.platform.lawconnectplatform.tracking.domain.services.StatusTypeCommandService;
import com.dartlinwave.platform.lawconnectplatform.tracking.infrastructure.persistence.jpa.repositories.StatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusCommandServiceImpl implements StatusCommandService {
    private final StatusRepository statusRepository;

    public StatusCommandServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public void handle(CreateStatusCommand command) {
        var isStatusWithSameLegalCaseId = statusRepository.existsStatusByLegalCaseId(command.legalCaseId());
        if (isStatusWithSameLegalCaseId) {
            throw new LegalCaseAlreadyHaveStatusException(
                    "Legal case with id: " + command.legalCaseId() + " already have status");
        }
        var status = new Status(command);
        statusRepository.save(status);
    }
}
