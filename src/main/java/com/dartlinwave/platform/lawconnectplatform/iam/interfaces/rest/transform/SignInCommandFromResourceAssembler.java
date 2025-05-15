package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.transform;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SignInCommand;
import com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources.SignInResource;

/**
 * Assembler for converting a {@link SignInResource} into a {@link SignInCommand}
 * for use in the application layer.
 */
public class SignInCommandFromResourceAssembler {

    /**
     * Converts a {@link SignInResource} into a {@link SignInCommand}.
     *
     * @param signInResource the resource containing sign-in data
     * @return the corresponding {@code SignInCommand}
     */
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}