package com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.commands;

/**
 * Command representing the creation of a Lawyer profile.
 * <p>
 * Contains the lawyer's personal and professional information required for registration.
 * </p>
 *
 * @param name        the first name of the lawyer
 * @param lastname    the last name of the lawyer
 * @param dni         the national identification number of the lawyer (must be 8 characters)
 * @param phone       the phone number of the lawyer (must be 9 characters)
 * @param description a brief description of the lawyer's background or expertise
 */
public record CreateLawyerCommand(
        String name,
        String lastname,
        String dni,
        String phone,
        String description
) {
    /**
     * Validates the fields of the create lawyer command.
     * Ensures that all fields are non-null, non-blank (where applicable), and meet specific format requirements.
     *
     * @throws IllegalArgumentException if any field is invalid
     */
    public CreateLawyerCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Lastname cannot be null or blank");
        }
        if (dni == null || dni.length() != 8) {
            throw new IllegalArgumentException("DNI must be exactly 8 characters long");
        }
        if (phone == null || phone.length() != 9) {
            throw new IllegalArgumentException("Phone must be exactly 9 characters long");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
    }
}