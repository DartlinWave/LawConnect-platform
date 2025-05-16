package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources.*;
import com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.transform.*;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.services.UserCommandService;

/**
 * REST controller responsible for handling authentication and user registration requests.
 * <p>
 * Exposes endpoints for:
 * <ul>
 *     <li>User sign-in</li>
 *     <li>Lawyer sign-up</li>
 *     <li>Client sign-up</li>
 * </ul>
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;

    /**
     * Constructs the AuthenticationController with the required user command service.
     *
     * @param userCommandService the service handling user-related commands
     */
    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    /**
     * Authenticates a user with the provided credentials.
     *
     * @param signInResource the resource containing username and password
     * @return a {@link ResponseEntity} containing the authenticated user resource if credentials are valid,
     * or 404 Not Found if authentication fails
     */
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);

        var authenticatedUser = userCommandService.handle(signInCommand);

        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler
                .toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());

        return ResponseEntity.ok(authenticatedUserResource);
    }

    /**
     * Registers a new lawyer in the system.
     *
     * @param signUpLawyerResource the resource containing lawyer registration data
     * @return a {@link ResponseEntity} containing the created user resource and HTTP 201 status,
     * or 400 Bad Request if registration fails
     */
    @Operation(summary = "Create Lawyer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lawyer created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping("/sign-up/lawyer")
    public ResponseEntity<UserResource> signUpLawyer(@RequestBody SignUpLawyerCommandResource signUpLawyerResource) {
        var signUpCommand = SignUpLawyerCommandFromResourceAssembler.toCommandFromResource(signUpLawyerResource);
        var user = userCommandService.handle(signUpCommand);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    /**
     * Registers a new client in the system.
     *
     * @param signUpClientResource the resource containing client registration data
     * @return a {@link ResponseEntity} containing the created user resource and HTTP 201 status,
     * or 400 Bad Request if registration fails
     */
    @Operation(summary = "Create Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping("/sign-up/client")
    public ResponseEntity<UserResource> signUpClient(@RequestBody SignUpClientResource signUpClientResource) {
        var signUpCommand = SignUpClientCommandFromResourceAssembler.toCommandFromResource(signUpClientResource);
        var user = userCommandService.handle(signUpCommand);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
}