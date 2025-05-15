package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.transform;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SignUpLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources.SignUpLawyerResource;

/**
 * Assembler for converting a {@link SignUpLawyerResource} resource
 * into a {@link SignUpLawyerCommand} command for use in the application layer.
 */
public class SignUpLawyerCommandFromResourceAssembler {

    /**
     * Converts a {@link SignUpLawyerResource} into a {@link SignUpLawyerCommand}.
     *
     * @param resource the resource containing sign-up data for a lawyer
     * @return the corresponding {@code SignUpLawyerCommand}
     */
    public static SignUpLawyerCommand toCommandFromResource(SignUpLawyerResource resource) {
        return new SignUpLawyerCommand(
                resource.name(),
                resource.lastname(),
                resource.specialty(),
                resource.description(),
                resource.phone(),
                resource.dni(),
                resource.username(),
                resource.password()
        );
    }
}