package com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands;

/**
 * Command representing a user sign-in request.
 * Contains the username and password required for authentication.
 *
 * @param username the username of the user attempting to sign in
 * @param password the password of the user attempting to sign in
 */
public record SignInCommand(String username, String password) {
}