package com.dartlinwave.platform.lawconnectplatform.iam.domain.model.entities;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Entity representing a user role in the LawConnect platform.
 * Each role is associated with a specific set of permissions defined by the {@link Roles} enum.
 */
@With
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    /**
     * Unique identifier for the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the role, represented by the {@link Roles} enum.
     */
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Roles name;

    /**
     * Constructs a Role with the specified {@link Roles} name.
     *
     * @param name the role name
     */
    public Role(Roles name) {
        this.name = name;
    }

    /**
     * Returns the string representation of the role name.
     *
     * @return the role name as a string
     */
    public String getStringName() {
        return name.toString();
    }

    /**
     * Returns the default role, which is {@link Roles#ROLE_ADMIN}.
     *
     * @return the default Role
     */
    public static Role getDefaultRole() {
        return new Role(Roles.ROLE_ADMIN);
    }

    /**
     * Converts a string to a Role object using the {@link Roles} enum.
     *
     * @param name the name of the role as a string
     * @return the corresponding Role object
     * @throws IllegalArgumentException if the name does not match any enum constant
     */
    public static Role toRoleFromName(String name) {
        return new Role(Roles.valueOf(name));
    }

    /**
     * Validates a list of roles. If the list is null or empty, returns a list containing the default role.
     *
     * @param roles the list of roles to validate
     * @return the original list if not empty, otherwise a list with the default role
     */
    public static List<Role> validateRoleSet(List<Role> roles) {
        if (roles == null || roles.isEmpty())
            return List.of(getDefaultRole());

        return roles;
    }
}