package com.dartlinwave.platform.lawconnectplatform.profiles.interfaces.rest.resources;

/**
 * Resource representation of a Client for REST API responses.
 * <p>
 * Encapsulates client profile data to be sent to API consumers.
 * </p>
 *
 * @param userId   the unique identifier of the user associated with the client
 * @param clientId the unique identifier of the client entity
 * @param fullName the full name of the client
 * @param dni      the client's national identification number (DNI)
 * @param phone    the client's phone number
 */
public record ClientResource(
        Long userId,
        Long clientId,
        String fullName,
        String dni,
        String phone
) {
}