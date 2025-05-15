package com.dartlinwave.platform.lawconnectplatform.profiles.application.internal.queryservices;

import org.springframework.stereotype.Service;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.aggregates.Client;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.services.ClientQueryService;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.queries.GetClientByUserIdQuery;
import com.dartlinwave.platform.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories.ClientRepository;

import java.util.Optional;

/**
 * Service implementation for handling client query operations.
 * <p>
 * Provides logic to retrieve client profiles based on queries.
 * </p>
 */
@Service
public class ClientQueryServiceImpl implements ClientQueryService {

    /**
     * Repository for client persistence operations.
     */
    private final ClientRepository clientRepository;

    /**
     * Constructs a new {@code ClientQueryServiceImpl} with the given {@link ClientRepository}.
     *
     * @param clientRepository the repository for client persistence
     */
    public ClientQueryServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Retrieves a client by the associated user ID.
     *
     * @param query the query object containing the user ID
     * @return an {@link Optional} containing the {@link Client} if found, or empty if not found
     */
    @Override
    public Optional<Client> handle(GetClientByUserIdQuery query) {
        return clientRepository.findClientByUserId(query.userId());
    }
}