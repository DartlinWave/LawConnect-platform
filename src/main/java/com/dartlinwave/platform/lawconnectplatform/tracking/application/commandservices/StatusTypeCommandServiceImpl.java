package com.dartlinwave.platform.lawconnectplatform.tracking.application.commandservices;

import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.commands.SeedStatusTypeCommand;
import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.entities.StatusType;
import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.valueobjects.StatusTypes;
import com.dartlinwave.platform.lawconnectplatform.tracking.domain.services.StatusTypeCommandService;
import com.dartlinwave.platform.lawconnectplatform.tracking.infrastructure.persistence.jpa.repositories.StatusTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StatusTypeCommandServiceImpl implements StatusTypeCommandService {

    private final StatusTypeRepository statusTypeRepository;

    public StatusTypeCommandServiceImpl(StatusTypeRepository statusTypeRepository) {
        this.statusTypeRepository = statusTypeRepository;
    }
    @Override
    public void handle(SeedStatusTypeCommand command) {
        var types = StatusTypes.values();
        Arrays.stream(types)
                .forEach(type -> {
                    if (!statusTypeRepository.existsStatusTypesByType(type)) {
                        var statusTypeEntity = new StatusType(type);
                        statusTypeRepository.save(statusTypeEntity);
                    }
                });
    }
}
