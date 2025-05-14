package com.dartlinwave.platform.lawconnectplatform.iam.domain.model.aggregates;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.entities.Role;
import com.dartlinwave.platform.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

/**
 * Aggregate root representing a user in the system.
 * A user has a unique username, a password, and a set of roles for authorization.
 */
@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    /**
     * The unique username of the user.
     */
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    /**
     * The password of the user.
     */
    @NotBlank
    @Size(max = 250)
    private String password;

    /**
     * The set of roles assigned to the user.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    /**
     * Default constructor initializing the roles set.
     */
    public User() {
        this.roles = new HashSet<>();
    }

    /**
     * Constructs a user with the specified username and password.
     * Initializes the roles set as empty.
     *
     * @param username the unique username
     * @param password the user's password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = new HashSet<>();
    }

    /**
     * Constructs a user with the specified username, password, and roles.
     *
     * @param username the unique username
     * @param password the user's password
     * @param roles    the set of roles to assign to the user
     */
    public User(String username, String password, Set<Role> roles) {
        this(username, password);
        addRoles(roles);
    }

    /**
     * Adds a single role to the user.
     *
     * @param role the role to add
     * @return the updated user instance
     */
    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    /**
     * Adds multiple roles to the user after validation.
     *
     * @param roles the set of roles to add
     */
    public void addRoles(Set<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
    }

    /**
     * Returns a set of the user's role names as strings.
     *
     * @return set of role names
     */
    public Set<String> getSerializedRoles() {
        return this.roles.stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());
    }

    /**
     * Verify if a user has a specific role.
     *
     * @param role the role to check
     * @return true if the user has the role, false otherwise
     */
    public boolean hasRole(Role role) {
        return this.roles.contains(role);
    }
}