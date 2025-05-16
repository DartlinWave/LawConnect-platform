package com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.transforms;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.Specialty;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.CreateCaseCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.resources.CreateCaseResource;

/**
 * Assembler for converting a {@link CreateCaseResource} resource
 * into a {@link CreateCaseCommand} command for use in the application layer.
 */
public class CreateCaseCommandFromResourceAssembler {

    /**
     * Converts a {@link CreateCaseResource} into a {@link CreateCaseCommand}.
     *
     * @param resource the resource containing data for creating a case
     * @return the corresponding {@code CreateCaseCommand}
     */
    public static CreateCaseCommand toCommandFromResource(CreateCaseResource resource) {
        return new CreateCaseCommand(
                resource.clientId(),
                resource.title(),
                resource.description(),
                Specialty.valueOf(resource.requiredSpecialty())
        );
    }
}