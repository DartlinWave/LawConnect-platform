package com.dartlinwave.platform.lawconnectplatform.profiles.interfaces.rest.transform;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.aggregates.Client;
import com.dartlinwave.platform.lawconnectplatform.profiles.interfaces.rest.resources.ClientResource;

/**
 * Assembler utility for converting {@link Client} entities to {@link ClientResource} representations.
 * <p>
 * Facilitates the transformation of domain entities into REST API resources.
 * </p>
 */
public class ClientResourceFromEntityAssembler {

    /**
     * Converts a {@link Client} entity to a {@link ClientResource} for API responses.
     *
     * @param client the client entity to convert
     * @return a resource representation of the client
     */
    public static ClientResource toResourceFromEntity(Client client) {
        return new ClientResource(
                client.getUserId(),
                client.getId(),
                client.getFullName(),
                client.getDni(),
                client.getPhone()
        );
    }
}