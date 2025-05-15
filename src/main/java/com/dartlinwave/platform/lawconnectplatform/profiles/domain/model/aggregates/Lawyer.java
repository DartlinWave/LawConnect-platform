package com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.aggregates;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.valueobjects.PersonName;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.entities.LawyerSpecialty;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.commands.CreateLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Setter;
import lombok.Getter;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import jakarta.persistence.*;

/**
 * Aggregate root representing a Lawyer profile within the system.
 * <p>
 * Encapsulates the lawyer's personal information, professional description,
 * specialties, and main specialty identifier. This entity is the main entry point
 * for managing lawyer-related data and business logic.
 * </p>
 *
 * <p>
 * The class uses JPA annotations for persistence and validation annotations to
 * enforce business rules at the attribute level.
 * </p>
 */
@Setter
@Getter
@Entity
public class Lawyer extends AuditableAbstractAggregateRoot<Lawyer> {

    /**
     * The unique identifier for the client.
     * This is a generated value and serves as the primary key.
     */
    @NotNull(message = "User ID cannot be null")
    @Positive(message = "User ID must be positive")
    private Long userId;

    /**
     * The lawyer's full name, represented as a value object.
     * Stored as embedded columns in the lawyer's table.
     */
    @Embedded
    private PersonName name;

    /**
     * The set of specialties associated with the lawyer.
     * Managed as a many-to-many relationship with the {@link LawyerSpecialty} entity.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "lawyer_specialties",
            joinColumns = @JoinColumn(name = "lawyer_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<LawyerSpecialty> specialties;

    /**
     * The lawyer's national identification number (DNI).
     * Must be exactly 8 characters and unique.
     */
    @NotBlank(message = "DNI cannot be blank")
    @Size(min = 8, max = 8, message = "DNI must be exactly 8 characters")
    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    /**
     * The lawyer's phone number.
     * Must be exactly 9 characters.
     */
    @NotBlank(message = "Phone cannot be blank")
    @Size(min = 9, max = 9, message = "Phone must be exactly 9 characters")
    @Column(nullable = false, length = 9)
    private String phone;

    /**
     * The lawyer's professional description.
     * Maximum length is 500 characters.
     */
    @NotBlank(message = "Description cannot be blank")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(nullable = false, length = 500)
    private String description;

    /**
     * Default constructor initializing the specialties set.
     */
    public Lawyer() {
        this.specialties = new HashSet<>();
    }

    /**
     * Constructs a Lawyer with the specified personal and professional information.
     *
     * @param name        the lawyer's first name
     * @param lastname    the lawyer's last name
     * @param dni         the lawyer's DNI (must be 8 characters)
     * @param phone       the lawyer's phone number (must be 9 characters)
     * @param description the lawyer's professional description
     */
    public Lawyer(String name, String lastname, String dni, String phone, String description) {
        this.name = new PersonName(name, lastname);
        this.dni = dni;
        this.phone = phone;
        this.description = description;
        this.specialties = new HashSet<>();
    }

    /**
     * Constructs a new {@code Lawyer} aggregate from a {@link CreateLawyerCommand} and a set of specialties.
     * Initializes all required fields from the command object and adds the provided specialties.
     *
     * @param command     the command containing the lawyer's registration data
     * @param specialties the set of specialties to assign to the lawyer
     * @param userId      the unique identifier for the user associated with this lawyer
     */
    public Lawyer(CreateLawyerCommand command, Set<LawyerSpecialty> specialties, Long userId) {
        this(
                command.name(),
                command.lastname(),
                command.dni(),
                command.phone(),
                command.description()
        );

        this.userId = userId;
        addSpecialities(specialties);
    }

    /**
     * Adds a set of specialties to the lawyer after validation.
     * If the provided set is null or empty, the default specialty is added.
     *
     * @param specialties the set of specialties to add
     */
    public void addSpecialities(Set<LawyerSpecialty> specialties) {
        var validateSpecialitiesSet = LawyerSpecialty.validateLawyerSpecialtySet(specialties);
        this.specialties.addAll(validateSpecialitiesSet);
    }

    /**
     * Returns a set of the lawyer's specialty names as strings.
     *
     * @return set of specialty names
     */
    public Set<String> getSerializedSpecialties() {
        return this.specialties.stream()
                .map(specialty -> specialty.getName().name())
                .collect(Collectors.toSet());
    }

    /**
     * Returns the lawyer's full name by delegating to the {@link PersonName} value object.
     *
     * @return the full name of the lawyer in the format "name lastname"
     */
    public String getFullName() {
        return name.getFullName();
    }
}