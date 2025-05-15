package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources;

/**
 * Resource representing the credentials required for user sign-in.
 *
 * @param username the username of the user attempting to sign in
 * @param password the password of the user attempting to sign in
 */
public record SignInResource(String username, String password) {
}