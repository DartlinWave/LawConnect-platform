package com.dartlinwave.platform.lawconnectplatform.profiles.application.internal.commandservices;

import com.dartlinwave.platform.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.aggregates.Client;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.services.ClientCommandService;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.commands.CreateClientCommand;

import java.util.Optional;

/**
 * Service implementation for handling client-related commands.
 * <p>
 * Provides business logic for creating and managing client profiles.
 * </p>
 */
@Service
public class ClientCommandServiceImpl implements ClientCommandService {

    private final ClientRepository clientRepository;

    /**
     * Constructs a new {@code ClientCommandServiceImpl} with the required dependencies.
     *
     * @param clientRepository the repository for client persistence
     */
    public ClientCommandServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Handles the creation of a new client profile.
     * <p>
     * Validates the uniqueness of the client's DNI and persists the new client entity.
     * </p>
     *
     * @param command the command containing client creation details
     * @param userId  the user ID associated with the client
     * @return an {@link Optional} containing the created {@link Client}, or empty if creation fails
     * @throws IllegalArgumentException if the DNI already exists
     */
    @Override
    public Optional<Client> handle(CreateClientCommand command, Long userId) {

        if (clientRepository.existsByDni(command.dni()))
            throw new IllegalArgumentException("DNI already exists");

        var client = new Client(command, userId);

        var savedClient = clientRepository.save(client);

        return Optional.of(savedClient);
    }
}