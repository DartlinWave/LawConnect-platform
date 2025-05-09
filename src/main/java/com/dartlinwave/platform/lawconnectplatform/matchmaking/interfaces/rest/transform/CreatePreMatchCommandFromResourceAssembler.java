package com.dartlinwave.platform.lawconnectplatform.matchmaking.interfaces.rest.transform;

import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands.CreatePreMatchCommand;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.interfaces.rest.resources.CreatePreMatchResource;

public class CreatePreMatchCommandFromResourceAssembler {
    public static CreatePreMatchCommand toCommandFromResource(CreatePreMatchResource resource) {
        return new CreatePreMatchCommand(
                resource.caseId(),
                resource.lawyerId(),
                resource.matchStatus()
        );
    }
}
