package com.dartlinwave.platform.lawconnectplatform.iam.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.aggregates.User;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SignInCommand;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SignUpClientCommand;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SignUpLawyerCommand;

import java.util.Optional;

/**
 * Defines operations for handling user authentication and registration commands.
 */
public interface UserCommandService {

    /**
     * Authenticates a user based on the provided sign-in command.
     *
     * @param command the sign-in command containing user credentials
     * @return an {@link Optional} with a pair of the authenticated {@link User} and a token,
     * or empty if authentication fails
     */
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);

    /**
     * Registers a new lawyer using the provided sign-up command.
     *
     * @param command the sign-up command with lawyer registration details
     * @return an {@link Optional} containing the created {@link User}, or empty if registration fails
     */
    Optional<User> handle(SignUpLawyerCommand command);

    /**
     * Registers a new client using the provided sign-up command.
     *
     * @param command the sign-up command with client registration details
     * @return an {@link Optional} containing the created {@link User}, or empty if registration fails
     */
    Optional<User> handle(SignUpClientCommand command);
}