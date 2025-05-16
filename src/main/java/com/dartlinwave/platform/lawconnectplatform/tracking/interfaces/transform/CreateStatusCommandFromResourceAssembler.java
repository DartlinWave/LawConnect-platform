package com.dartlinwave.platform.lawconnectplatform.tracking.interfaces.transform;

import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.commands.CreateStatusCommand;
import com.dartlinwave.platform.lawconnectplatform.tracking.interfaces.resources.CreateStatusResource;

public class CreateStatusCommandFromResourceAssembler {
    public static CreateStatusCommand toCommandFromResource(CreateStatusResource resource) {
        return new CreateStatusCommand(
                resource.legalCaseId()
        );
    }
}
