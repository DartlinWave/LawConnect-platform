package com.dartlinwave.platform.lawconnectplatform.iam.domain.services;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SignInCommand;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SignUpClientCommand;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SignUpLawyerCommand;

/**
 * Service interface for handling user-related commands.
 * Provides operations for user sign-in and sign-up processes.
 */
public interface UserCommandService {

    /**
     * Handles the user sign-in process based on the provided command.
     *
     * @param command the command containing sign-in credentials
     */
    void handle(SignInCommand command);

    /**
     * Handles the sign-up process for a lawyer based on the provided command.
     *
     * @param command the command containing lawyer registration details
     */
    void handle(SignUpLawyerCommand command);

    /**
     * Handles the sign-up process for a client based on the provided command.
     *
     * @param command the command containing client registration details
     */
    void handle(SignUpClientCommand command);
}