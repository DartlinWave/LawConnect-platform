package com.dartlinwave.platform.lawconnectplatform.profiles.domain.services;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.aggregates.Client;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.commands.CreateClientCommand;

import java.util.Optional;

/**
 * Service interface for handling client-related commands.
 * <p>
 * Provides operations to manage client profiles in the system.
 * </p>
 */
public interface ClientCommandService {

    /**
     * Handles the creation of a new client profile based on the provided command.
     *
     * @param command the command containing the client's registration data
     * @return an {@link Optional} containing the created {@link Client} if successful, or empty if creation failed
     */
    Optional<Client> handle(CreateClientCommand command);
}