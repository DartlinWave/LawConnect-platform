package com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.aggregates;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.valueobjects.PersonName;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.commands.CreateClientCommand;
import com.dartlinwave.platform.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

/**
 * Aggregate root representing a Client profile within the system.
 * <p>
 * Encapsulates the client's personal information, including name, DNI, and phone number.
 * This entity is the main entry point for managing client-related data and business logic.
 * </p>
 *
 * <p>
 * The class uses JPA annotations for persistence and validation annotations to
 * enforce business rules at the attribute level.
 * </p>
 */
@Getter
@Setter
@Entity
public class Client extends AuditableAbstractAggregateRoot<Client> {

    /**
     * The unique identifier for the client.
     * This is a generated value and serves as the primary key.
     */
    @NotNull(message = "User ID cannot be null")
    @Positive(message = "User ID must be positive")
    private Long userId;

    /**
     * The client's full name, represented as a value object.
     * Stored as embedded columns in the client's table.
     */
    @Embedded
    private PersonName name;

    /**
     * The client's national identification number (DNI).
     * Must be exactly 8 characters and unique.
     */
    @NotBlank(message = "DNI cannot be blank")
    @Size(min = 8, max = 8, message = "DNI must be exactly 8 characters")
    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    /**
     * The client's phone number.
     * Must be exactly 9 characters.
     */
    @NotBlank(message = "Phone cannot be blank")
    @Size(min = 9, max = 9, message = "Phone must be exactly 9 characters")
    @Column(nullable = false, length = 9)
    private String phone;

    /**
     * Default constructor for JPA.
     */
    public Client() {
    }

    /**
     * Constructs a Client with the specified personal information.
     *
     * @param name     the client's first name
     * @param lastname the client's last name
     * @param dni      the client's DNI (must be 8 characters)
     * @param phone    the client's phone number (must be 9 characters)
     */
    public Client(String name, String lastname, String dni, String phone) {
        this.name = new PersonName(name, lastname);
        this.dni = dni;
        this.phone = phone;
    }

    /**
     * Constructs a new {@code Client} aggregate from a {@link CreateClientCommand}.
     * Initializes all required fields from the command object.
     *
     * @param command the command containing the client's registration data
     * @param userId the unique identifier for the user associated with this client
     */
    public Client(CreateClientCommand command, Long userId) {
        this(
                command.name(),
                command.lastname(),
                command.dni(),
                command.phone()
        );
        this.userId = userId;
    }
}