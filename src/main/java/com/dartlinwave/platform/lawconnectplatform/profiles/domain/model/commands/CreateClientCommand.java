package com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.commands;

/**
 * Command representing the creation of a Client profile.
 * <p>
 * Contains the client's personal information required for registration.
 * </p>
 *
 * @param name     the first name of the client
 * @param lastname the last name of the client
 * @param dni      the national identification number of the client (must be 8 characters)
 * @param phone    the phone number of the client (must be 9 characters)
 */
public record CreateClientCommand(
        String name,
        String lastname,
        String dni,
        String phone
) {
    /**
     * Validates the fields of the create client command.
     * Ensures that all fields are non-null, non-blank (where applicable), and meet specific format requirements.
     *
     * @throws IllegalArgumentException if any field is invalid
     */
    public CreateClientCommand {
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
    }
}