package com.dartlinwave.platform.lawconnectplatform.iam.interfaces.rest.resources;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.valueobjects.LawyerSpecialty;

/**
 * Resource representing the information required for a lawyer to sign up.
 *
 * @param name        the first name of the lawyer
 * @param lastname    the last name of the lawyer
 * @param specialty   the specialty of the lawyer
 * @param description a brief description of the lawyer's background or expertise
 * @param phone       the phone number of the lawyer
 * @param dni         the national identification number of the lawyer
 * @param username    the username chosen by the lawyer
 * @param password    the password chosen by the lawyer
 */
public record SignUpLawyerResource(String name, String lastname, LawyerSpecialty specialty,
                                   String description, String phone, String dni, String username, String password) {
}