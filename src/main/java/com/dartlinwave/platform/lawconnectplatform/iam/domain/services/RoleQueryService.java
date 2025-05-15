package com.dartlinwave.platform.lawconnectplatform.iam.domain.services;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.entities.Role;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.queries.GetAllRolesQuery;

import java.util.Set;

/**
 * Service interface for handling role-related queries.
 * <p>
 * Provides methods to retrieve roles from the system, typically used by the application or REST layer.
 * </p>
 */
public interface RoleQueryService {
    /**
     * Handles the retrieval of all roles in the system based on the provided query.
     *
     * @param query the query object specifying the request for all roles
     * @return a set of {@link Role} entities representing all roles in the system
     */
    Set<Role> handle(GetAllRolesQuery query);
}