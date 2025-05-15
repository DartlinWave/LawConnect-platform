package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.transform;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.entities.Role;
import com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources.RoleResource;

/**
 * Assembler for converting a {@link Role} entity into a {@link RoleResource}
 * for REST responses.
 */
public class RoleResourceFromEntityAssembler {

    /**
     * Converts a {@link Role} entity into a {@link RoleResource}.
     *
     * @param role the role entity to convert
     * @return the corresponding {@code RoleResource}
     */
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}