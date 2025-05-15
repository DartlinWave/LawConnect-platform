package com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands;

/**
 * Command representing a lawyer sign-up request.
 * <p>
 * Contains the lawyer's personal, professional, and authentication information required for registration.
 * </p>
 *
 * @param name        the first name of the lawyer
 * @param lastname    the last name of the lawyer
 * @param specialtyId the unique identifier of the lawyer's specialty
 * @param description a brief description of the lawyer's background or expertise
 * @param phone       the phone number of the lawyer (must be 9 digits)
 * @param dni         the national identification number of the lawyer (must be 8 digits)
 * @param username    the username for the lawyer's account
 * @param password    the password for the lawyer's account (must be at least 25 characters)
 */
public record SignUpLawyerCommand(
        String name,
        String lastname,
        Long specialtyId,
        String description,
        String phone,
        String dni,
        String username,
        String password
) {

    /**
     * Validates the fields of the sign-up command.
     * Ensures that all fields are non-null, non-blank, and meet specific format requirements.
     *
     * @throws IllegalArgumentException if any field is invalid
     */
    public SignUpLawyerCommand {
        if (specialtyId == null)
            throw new IllegalArgumentException("Specialty ID cannot be null");
       if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank");
        if (lastname == null || lastname.isBlank())
            throw new IllegalArgumentException("Lastname cannot be null or blank");
        if (username == null || username.isBlank())
            throw new IllegalArgumentException("Username cannot be null or blank");
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password cannot be null or blank");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description cannot be null or blank");
        if (dni == null || dni.isBlank() || dni.length() != 8 || !dni.matches("\\d+"))
            throw new IllegalArgumentException("DNI cannot be null, blank, or different from 8 characters");
        if (phone == null || phone.isBlank() || phone.length() != 9 || !phone.matches("\\d+"))
            throw new IllegalArgumentException("Phone cannot be null, blank, or different from 9 characters");
    }
}