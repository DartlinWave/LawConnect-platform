package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources;

import java.util.Set;

/**
 * Resource representing a user with their unique identifier, username, and assigned roles.
 *
 * @param id       the unique identifier of the user
 * @param username the username of the user
 * @param roles    the set of roles assigned to the user
 */
public record UserResource(Long id, String username, Set<String> roles) {
}