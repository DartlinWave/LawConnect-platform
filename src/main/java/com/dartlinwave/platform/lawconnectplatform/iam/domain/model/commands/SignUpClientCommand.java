package com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands;

/**
 * Command representing a client sign-up request.
 * Contains the client's personal and authentication information required for registration.
 *
 * @param name     the first name of the client
 * @param lastname the last name of the client
 * @param phone    the phone number of the client (must be 9 digits)
 * @param dni      the national identification number of the client (must be 8 digits)
 * @param username the username for the client's account
 * @param password the password for the client's account (must be at least 25 characters)
 */
public record SignUpClientCommand(String name, String lastname,
                                  String phone, String dni, String username, String password) {

    /**
     * Validates the fields of the sign-up command.
     * Ensures that all fields are non-null, non-blank, and meet specific format requirements.
     *
     * @throws IllegalArgumentException if any field is invalid
     */
    public SignUpClientCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Lastname cannot be null or blank");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
        if (dni == null || dni.isBlank() || dni.length() != 8 || !dni.matches("\\d+")) {
            throw new IllegalArgumentException("DNI cannot be null, blank, or different from 8 characters");
        }
        if (password == null || password.isBlank() || password.length() < 25) {
            throw new IllegalArgumentException("Password cannot be null, blank, or less than 25 characters");
        }
        if (phone == null || phone.isBlank() || phone.length() != 9 || !phone.matches("\\d+")) {
            throw new IllegalArgumentException("Phone cannot be null, blank, or different from 9 characters");
        }
    }
}