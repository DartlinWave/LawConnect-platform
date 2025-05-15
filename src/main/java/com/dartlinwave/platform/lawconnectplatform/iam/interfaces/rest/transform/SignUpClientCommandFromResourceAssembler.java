package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.transform;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SignUpClientCommand;
import com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources.SignUpClientResource;

/**
 * Assembler for converting a {@link SignUpClientResource} resource
 * into a {@link SignUpClientCommand} command for use in the application layer.
 */
public class SignUpClientCommandFromResourceAssembler {

    /**
     * Converts a {@link SignUpClientResource} into a {@link SignUpClientCommand}.
     *
     * @param resource the resource containing sign-up data for a client
     * @return the corresponding {@code SignUpClientCommand}
     */
    public static SignUpClientCommand toCommandFromResource(SignUpClientResource resource) {
        return new SignUpClientCommand(
                resource.name(),
                resource.lastname(),
                resource.phone(),
                resource.dni(),
                resource.username(),
                resource.password()
        );
    }
}