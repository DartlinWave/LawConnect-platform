package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest;

import org.springframework.http.MediaType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.services.UserQueryService;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.queries.GetUserByIdQuery;
import com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources.UserResource;
import com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
/**
 * REST controller for managing users in the system.
 * <p>
 * Exposes endpoints for retrieving user information.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {

    private final UserQueryService userQueryService;

    /**
     * Constructs a new {@code UsersController} with the given {@link UserQueryService}.
     *
     * @param userQueryService the service for querying users
     */
    public UsersController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param userId the unique identifier of the user
     * @return a {@link ResponseEntity} containing the {@link UserResource} if found, or 404 if not found
     */
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);

        if (user.isEmpty())
            return ResponseEntity.notFound().build();

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }
}