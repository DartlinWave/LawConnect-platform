package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.transform;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SignUpLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources.SignUpLawyerCommandResource;

/**
 * Utility class for converting {@link SignUpLawyerCommandResource} objects
 * to {@link SignUpLawyerCommand} instances for application use.
 */
public class SignUpLawyerCommandFromResourceAssembler {

    /**
     * Maps a {@link SignUpLawyerCommandResource} to a {@link SignUpLawyerCommand}.
     *
     * @param resource the resource containing lawyer sign-up data
     * @return a new {@code SignUpLawyerCommand} with the provided data
     */
    public static SignUpLawyerCommand toCommandFromResource(SignUpLawyerCommandResource resource) {
        return new SignUpLawyerCommand(
                resource.name(),
                resource.lastname(),
                resource.specialties(),
                resource.description(),
                resource.phone(),
                resource.dni(),
                resource.username(),
                resource.password()
        );
    }
}