package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources;

import java.util.Set;

/**
 * Resource representing the data required for a lawyer registration request.
 *
 * @param name         the lawyer's first name
 * @param lastname     the lawyer's last name
 * @param specialties  a set of specialties that the lawyer is associated with
 * @param description  a brief description of the lawyer's background or expertise
 * @param phone        the lawyer's phone number
 * @param dni          the lawyer's national identification number
 * @param username     the username chosen by the lawyer
 * @param password     the password chosen by the lawyer
 */
public record SignUpLawyerCommandResource(
        String name,
        String lastname,
        Set<String> specialties,
        String description,
        String phone,
        String dni,
        String username,
        String password
) {
}