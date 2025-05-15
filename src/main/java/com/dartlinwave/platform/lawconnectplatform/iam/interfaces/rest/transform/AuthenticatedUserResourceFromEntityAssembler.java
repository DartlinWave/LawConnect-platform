package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.transform;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.aggregates.User;
import com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources.AuthenticatedUserResource;

/**
 * Assembler for converting a {@link User} entity and authentication token
 * into an {@link AuthenticatedUserResource} for REST responses.
 */
public class AuthenticatedUserResourceFromEntityAssembler {

    /**
     * Converts a {@link User} entity and authentication token into an {@link AuthenticatedUserResource}.
     *
     * @param user  the user entity to convert
     * @param token the authentication token to include in the resource
     * @return the corresponding {@code AuthenticatedUserResource}
     */
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token, user.getSerializedRoles());
    }
}