package com.dartlinwave.platform.lawconnectplatform.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.entities.Role;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.services.RoleQueryService;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.queries.GetAllRolesQuery;
import com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service implementation for handling role query operations.
 * <p>
 * Provides logic to retrieve all roles from the system based on queries.
 * </p>
 */
@Service
public class RoleQueryServiceImpl implements RoleQueryService {
    private final RoleRepository roleRepository;

    /**
     * Constructs a new {@code RoleQueryServiceImpl} with the given {@link RoleRepository}.
     *
     * @param roleRepository the repository for role persistence
     */
    public RoleQueryServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Retrieves all roles in the system based on the provided query.
     *
     * @param query the query object specifying the request for all roles
     * @return a set of {@link Role} entities representing all roles in the system
     */
    @Override
    public Set<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll()
                .stream().collect(Collectors.toSet());
    }
}