package com.dartlinwave.platform.lawconnectplatform.profiles.interfaces.rest.resources;

import java.util.Set;

/**
 * Resource representation of a Lawyer for REST API responses.
 * <p>
 * Encapsulates lawyer profile data to be sent to API consumers.
 * </p>
 *
 * @param userId      the unique identifier of the user associated with the lawyer
 * @param lawyerId    the unique identifier of the lawyer entity
 * @param fullName    the full name of the lawyer
 * @param dni         the lawyer's national identification number (DNI)
 * @param phone       the lawyer's phone number
 * @param description the lawyer's professional description or bio
 */
public record LawyerResource(
        Long userId,
        Long lawyerId,
        String fullName,
        String dni,
        String phone,
        String description,
        Set<String> specialties
) {
}