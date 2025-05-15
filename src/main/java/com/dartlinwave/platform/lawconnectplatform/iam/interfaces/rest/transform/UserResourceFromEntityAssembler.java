package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.transform;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.entities.Role;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.aggregates.User;
import com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources.UserResource;

import java.util.stream.Collectors;

/**
 * Assembler for converting a {@link User} entity into a {@link UserResource}
 * for REST responses.
 */
public class UserResourceFromEntityAssembler {

    /**
     * Converts a {@link User} entity into a {@link UserResource}.
     *
     * @param user the user entity to convert
     * @return the corresponding {@code UserResource}
     */
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream()
                .map(Role::getStringName).collect(Collectors.toSet());

        return new UserResource(user.getId(), user.getUsername(), roles);
    }
}