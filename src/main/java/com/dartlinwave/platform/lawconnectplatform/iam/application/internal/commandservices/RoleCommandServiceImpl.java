package com.dartlinwave.platform.lawconnectplatform.iam.application.internal.commandservices;

import org.springframework.stereotype.Service;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.entities.Role;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.valueobjects.Roles;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.services.RoleCommandService;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SeedRolesCommand;
import com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;

import java.util.Arrays;

/**
 * Service implementation for handling role-related commands.
 * <p>
 * Provides logic to seed roles into the persistence layer if they do not already exist.
 * </p>
 */
@Service
public class RoleCommandServiceImpl implements RoleCommandService {

    private final RoleRepository roleRepository;

    /**
     * Constructs a new {@code RoleCommandServiceImpl} with the given {@link RoleRepository}.
     *
     * @param roleRepository the repository used to access and persist roles
     */
    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Handles the {@link SeedRolesCommand} by seeding all roles defined in {@link Roles}
     * into the repository if they do not already exist.
     *
     * @param command the command containing the seeding request
     */
    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
           if (!roleRepository.existsByName(role)) {
               roleRepository.save(new Role(Roles.valueOf(role.name())));
           }
        });
    }
}