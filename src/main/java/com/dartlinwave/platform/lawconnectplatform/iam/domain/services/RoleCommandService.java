package com.dartlinwave.platform.lawconnectplatform.iam.domain.services;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SeedRolesCommand;

/**
 * Service interface for handling role-related commands.
 * Provides operations to manage roles in the system.
 */
public interface RoleCommandService {

    /**
     * Handles the seeding of roles based on the provided command.
     *
     * @param command the command containing the roles to seed
     */
    void handle(SeedRolesCommand command);
}