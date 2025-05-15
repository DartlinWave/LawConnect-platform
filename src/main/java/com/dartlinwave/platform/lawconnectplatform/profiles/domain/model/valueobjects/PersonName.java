package com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.valueobjects;

/**
 * Value object representing a person's name, consisting of a first name and a last name.
 * <p>
 * Ensures that both name and lastname are non-null and non-blank.
 * </p>
 *
 * @param name     the person's first name
 * @param lastname the person's last name
 */
public record PersonName(String name, String lastname) {

    /**
     * Default constructor initializing both name and lastname to null.
     */
    public PersonName() {
        this(null, null);
    }

    /**
     * Returns the full name by concatenating the first name and last name.
     *
     * @return the full name in the format "name lastname"
     */
    public String getFullName() {
        return "%s %s".formatted(name, lastname);
    }

    /**
     * Compact constructor that validates the name and lastname fields.
     *
     * @throws IllegalArgumentException if name or lastname is null or blank
     */
    public PersonName {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name must not be null or blank");

        if (lastname == null || lastname.isBlank())
            throw new IllegalArgumentException("Last name must not be null or blank");
    }
}