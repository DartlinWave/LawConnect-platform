package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources;

/**
 * Resource representing a user role with its unique identifier and name.
 *
 * @param id   the unique identifier of the role
 * @param name the name of the role
 */
public record RoleResource(Long id, String name) {
}