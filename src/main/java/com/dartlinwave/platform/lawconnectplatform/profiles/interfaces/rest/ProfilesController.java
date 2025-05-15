package com.dartlinwave.platform.lawconnectplatform.profiles.interfaces.rest;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.services.LawyerQueryService;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.services.ClientQueryService;
import com.dartlinwave.platform.lawconnectplatform.profiles.interfaces.rest.resources.LawyerResource;
import com.dartlinwave.platform.lawconnectplatform.profiles.interfaces.rest.resources.ClientResource;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.queries.GetClientByUserIdQuery;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.queries.GetLawyerByUserIdQuery;
import com.dartlinwave.platform.lawconnectplatform.profiles.interfaces.rest.transform.ClientResourceFromEntityAssembler;
import com.dartlinwave.platform.lawconnectplatform.profiles.interfaces.rest.transform.LawyerResourceFromEntityAssembler;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing profile-related endpoints.
 * <p>
 * Provides endpoints to retrieve client and lawyer profiles by user ID.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profiles Management Endpoints")
public class ProfilesController {

    private final ClientQueryService clientQueryService;
    private final LawyerQueryService lawyerQueryService;

    /**
     * Constructs a new {@code ProfilesController} with the required services.
     *
     * @param clientQueryService the service for querying client profiles
     * @param lawyerQueryService the service for querying lawyer profiles
     */
    public ProfilesController(ClientQueryService clientQueryService, LawyerQueryService lawyerQueryService) {
        this.clientQueryService = clientQueryService;
        this.lawyerQueryService = lawyerQueryService;
    }

    /**
     * Retrieves a client profile by the associated user ID.
     *
     * @param userId the unique identifier of the user
     * @return a {@link ResponseEntity} containing the {@link ClientResource} if found, or 404 if not found
     */
    @GetMapping(value = "/client/{userId}")
    public ResponseEntity<ClientResource> getClient(@PathVariable Long userId) {
        var getClientByUserIdQuery = new GetClientByUserIdQuery(userId);

        var client = clientQueryService.handle(getClientByUserIdQuery);

        if (client.isEmpty())
            return ResponseEntity.notFound().build();

        var clientResource = ClientResourceFromEntityAssembler.toResourceFromEntity(client.get());

        return ResponseEntity.ok(clientResource);
    }

    /**
     * Retrieves a lawyer profile by the associated user ID.
     *
     * @param userId the unique identifier of the user
     * @return a {@link ResponseEntity} containing the {@link LawyerResource} if found, or 404 if not found
     */
    @GetMapping(value = "/lawyer/{userId}")
    public ResponseEntity<LawyerResource> getLawyer(@PathVariable Long userId) {
        var getLawyerByUserIdQuery = new GetLawyerByUserIdQuery(userId);

        var lawyer = lawyerQueryService.handle(getLawyerByUserIdQuery);

        if (lawyer.isEmpty())
            return ResponseEntity.notFound().build();

        var lawyerResource = LawyerResourceFromEntityAssembler.toResourceFromEntity(lawyer.get());

        return ResponseEntity.ok(lawyerResource);
    }
}