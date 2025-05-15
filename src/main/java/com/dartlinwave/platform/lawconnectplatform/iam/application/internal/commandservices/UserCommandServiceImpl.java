package com.dartlinwave.platform.lawconnectplatform.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.entities.Role;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.aggregates.User;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.valueobjects.Roles;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.services.UserCommandService;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SignInCommand;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SignUpClientCommand;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SignUpLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.iam.application.internal.outboundservices.tokens.TokenService;
import com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.dartlinwave.platform.lawconnectplatform.iam.application.internal.outboundservices.hashing.HashingService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.Optional;

/**
 * Service implementation for handling user authentication and registration commands.
 * <p>
 * Provides logic for signing in users, registering lawyers, and registering clients,
 * including role assignment and password hashing.
 * </p>
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;

    /**
     * Constructs a new {@code UserCommandServiceImpl} with the required dependencies.
     *
     * @param tokenService    the service for token operations
     * @param userRepository  the repository for user persistence
     * @param roleRepository  the repository for role persistence
     * @param hashingService  the service for password hashing and verification
     */
    public UserCommandServiceImpl(
            TokenService tokenService,
            UserRepository userRepository,
            RoleRepository roleRepository,
            HashingService hashingService) {

        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
    }

    /**
     * Authenticates a user based on the provided sign-in command.
     * <p>
     * Validates the username and password, and generates a token if authentication succeeds.
     * </p>
     *
     * @param command the sign-in command containing user credentials
     * @return an {@link Optional} with a pair of the authenticated {@link User} and a token,
     *         or empty if authentication fails
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());

        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");

        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");

        var token = tokenService.generateToken(user.get().getUsername());

        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    /**
     * Registers a new lawyer using the provided sign-up command.
     * <p>
     * Checks for username uniqueness, assigns the lawyer role, hashes the password,
     * and persists the new user.
     * </p>
     *
     * @param command the sign-up command with lawyer registration details
     * @return an {@link Optional} containing the created {@link User}, or empty if registration fails
     * @throws RuntimeException if the username already exists or the lawyer role is not found
     */
    @Override
    @Transactional
    public Optional<User> handle(SignUpLawyerCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");

        Role lawyerRole = roleRepository.findByName(Roles.ROLE_LAWYER)
                .orElseThrow(() -> new RuntimeException("Lawyer role not found"));

        Set<Role> roles = Set.of(lawyerRole);

        var hashedPassword = hashingService.encode(command.password());

        var user = new User(command.username(), hashedPassword, roles);
        userRepository.save(user);

        /*TODO: Create Lawyer Profile*/

        return Optional.of(user);
    }

    /**
     * Registers a new client using the provided sign-up command.
     * <p>
     * Checks for username uniqueness, assigns the client role, hashes the password,
     * and persists the new user.
     * </p>
     *
     * @param command the sign-up command with client registration details
     * @return an {@link Optional} containing the created {@link User}, or empty if registration fails
     * @throws RuntimeException if the username already exists or the client role is not found
     */
    @Override
    @Transactional
    public Optional<User> handle(SignUpClientCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");

        Role clientrole = roleRepository.findByName(Roles.ROLE_CLIENT)
                .orElseThrow(() -> new RuntimeException("Client role not found"));

        Set<Role> roles = Set.of(clientrole);

        var hashedPassword = hashingService.encode(command.password());

        var user = new User(command.username(), hashedPassword, roles);
        userRepository.save(user);

        /*TODO: Create Client Profile*/

        return Optional.of(user);
    }
}