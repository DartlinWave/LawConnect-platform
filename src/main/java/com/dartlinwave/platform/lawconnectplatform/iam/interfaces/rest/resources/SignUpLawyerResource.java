package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources;

/**
 * Resource representing the data required for a lawyer registration request.
 *
 * @param name         the lawyer's first name
 * @param lastname     the lawyer's last name
 * @param specialtyId  the unique identifier of the lawyer's specialty
 * @param description  a brief description of the lawyer's background or expertise
 * @param phone        the lawyer's phone number
 * @param dni          the lawyer's national identification number
 * @param username     the username chosen by the lawyer
 * @param password     the password chosen by the lawyer
 */
public record SignUpLawyerResource(
        String name,
        String lastname,
        Long specialtyId,
        String description,
        String phone,
        String dni,
        String username,
        String password
) {
}