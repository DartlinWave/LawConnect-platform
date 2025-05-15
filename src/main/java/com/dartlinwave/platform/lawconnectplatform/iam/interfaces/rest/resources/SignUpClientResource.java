package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources;

/**
 * Resource representing the information required for a client to sign up.
 *
 * @param name     the first name of the client
 * @param lastname the last name of the client
 * @param phone    the phone number of the client
 * @param dni      the national identification number of the client
 * @param username the username chosen by the client
 * @param password the password chosen by the client
 */
public record SignUpClientResource(String name, String lastname,
                                   String phone, String dni, String username, String password) {
}