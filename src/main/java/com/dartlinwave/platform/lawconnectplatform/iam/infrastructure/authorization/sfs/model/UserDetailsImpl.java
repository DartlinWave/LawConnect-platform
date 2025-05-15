package com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.authorization.sfs.model;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.aggregates.User;

import java.util.Collection;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Implementation of {@link UserDetails} that represents user information
 * required by Spring Security for authentication and authorization.
 * <p>
 * This class adapts the application's {@link User} aggregate to the
 * {@link UserDetails} interface, including authorities and account status.
 * </p>
 */
@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

    /**
     * The username identifying the user.
     */
    private final String username;

    /**
     * The user's password (ignored in JSON serialization).
     */
    @JsonIgnore
    private final String password;

    /**
     * Indicates whether the user is enabled.
     */
    private final boolean enabled;

    /**
     * Indicates whether the user's account has expired.
     */
    private final boolean accountNonExpired;

    /**
     * Indicates whether the user is locked or unlocked.
     */
    private final boolean accountNonLocked;

    /**
     * Indicates whether the user's credentials (password) have expired.
     */
    private final boolean credentialsNonExpired;

    /**
     * The authorities granted to the user.
     */
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructs a new {@code UserDetailsImpl} with the specified username, password, and authorities.
     * All account status flags are set to {@code true} by default.
     *
     * @param username    the username identifying the user
     * @param password    the user's password
     * @param authorities the authorities granted to the user
     */
    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.enabled = true;
        this.password = password;
        this.accountNonLocked = true;
        this.accountNonExpired = true;
        this.authorities = authorities;
        this.credentialsNonExpired = true;
    }

    /**
     * Builds a {@code UserDetailsImpl} instance from the given {@link User} aggregate.
     * Maps the user's roles to {@link SimpleGrantedAuthority} instances.
     *
     * @param user the user aggregate from which to build the user details
     * @return a new {@code UserDetailsImpl} instance
     */
    public static UserDetailsImpl build(User user) {
        var authorities = user.getRoles().stream()
                .map(role -> role.getName().name())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}