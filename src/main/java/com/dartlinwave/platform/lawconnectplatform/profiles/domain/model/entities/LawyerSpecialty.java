package com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.entities;

import lombok.*;
import jakarta.persistence.*;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.valueobjects.LawyerSpecialties;

import java.util.Set;

/**
 * Entity representing a lawyer's specialty in the LawConnect platform.
 * Each specialty corresponds to a specific area of legal expertise, defined by the {@link LawyerSpecialties} enum.
 */
@With
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LawyerSpecialty {

    /**
     * Unique identifier for the lawyer specialty.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The specialty of the lawyer, represented by the {@link LawyerSpecialties} enum.
     */
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private LawyerSpecialties name;

    /**
     * Constructs a LawyerSpecialty with the specified specialty.
     *
     * @param name the lawyer's specialty
     */
    public LawyerSpecialty(LawyerSpecialties name) {
        this.name = name;
    }

    /**
     * Returns the string representation of the specialty.
     *
     * @return the specialty as a string
     */
    public String getStringSpecialty() {
        return name.toString();
    }

    /**
     * Returns the default specialty, which is {@link LawyerSpecialties#CIVIL_LAW}.
     *
     * @return the default LawyerSpecialty
     */
    public static LawyerSpecialty getDefaultSpecialty() {
        return new LawyerSpecialty(LawyerSpecialties.CIVIL_LAW);
    }

    /**
     * Converts a string to a LawyerSpecialty object using the {@link LawyerSpecialties} enum.
     *
     * @param name the name of the specialty as a string
     * @return the corresponding LawyerSpecialty object
     * @throws IllegalArgumentException if the name does not match any enum constant
     */
    public static LawyerSpecialty toLawyerSpecialtyFromName(String name) {
        return new LawyerSpecialty(LawyerSpecialties.valueOf(name));
    }

    /**
     * Validates a set of specialties. If the set is null or empty, returns a set containing the default specialty.
     *
     * @param specialties the set of specialties to validate
     * @return the original set if not empty, otherwise a set with the default specialty
     */
    public static Set<LawyerSpecialty> validateLawyerSpecialtySet(Set<LawyerSpecialty> specialties) {
        if (specialties == null || specialties.isEmpty())
            return Set.of(LawyerSpecialty.getDefaultSpecialty());

        return specialties;
    }
}