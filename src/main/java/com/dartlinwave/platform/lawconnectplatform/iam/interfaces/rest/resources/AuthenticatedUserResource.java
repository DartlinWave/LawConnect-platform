package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources;

import java.util.Set;

/**
 * Resource representing an authenticated user, including their ID, email, authentication token, and roles.
 *
 * @param id    the unique identifier of the user
 * @param email the email address of the user
 * @param token the authentication token issued to the user
 * @param roles the set of roles assigned to the user
 */
public record AuthenticatedUserResource(Long id, String email, String token, Set<String> roles) {
}