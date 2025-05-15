package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.services.RoleQueryService;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.queries.GetAllRolesQuery;
import com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources.RoleResource;
import com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.transform.RoleResourceFromEntityAssembler;

import java.util.List;

/**
 * REST controller for managing roles in the system.
 * <p>
 * Exposes endpoints for retrieving role information.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Roles", description = "Role Management Endpoints")
public class RolesController {
    private final RoleQueryService roleQueryService;

    /**
     * Constructs a new {@code RolesController} with the given {@link RoleQueryService}.
     *
     * @param roleQueryService the service for querying roles
     */
    public RolesController(RoleQueryService roleQueryService) {
        this.roleQueryService = roleQueryService;
    }

    /**
     * Retrieves all roles available in the system.
     *
     * @return a {@link ResponseEntity} containing a list of {@link RoleResource} objects representing all roles
     */
    @GetMapping
    public ResponseEntity<List<RoleResource>> getAllRoles() {
        var getAllRolesQuery = new GetAllRolesQuery();

        var roles = roleQueryService.handle(getAllRolesQuery);

        var roleResources = roles.stream()
                .map(RoleResourceFromEntityAssembler::toResourceFromEntity).toList();

        return ResponseEntity.ok(roleResources);
    }
}