package com.dartlinwave.platform.lawconnectplatform.profiles.domain.services;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.commands.CreateLawyerCommand;

import java.util.Optional;

/**
 * Service interface for handling lawyer-related commands.
 * <p>
 * Provides operations to manage lawyer profiles in the system.
 * </p>
 */
public interface LawyerCommandService {

    /**
     * Handles the creation of a new lawyer profile based on the provided command.
     *
     * @param command the command containing the lawyer's registration data
     * @param userId the unique identifier of the user creating the lawyer profile
     * @return an {@link Optional} containing the created {@link Lawyer} if successful, or empty if creation failed
     */
    Optional<Lawyer> handle(CreateLawyerCommand command, Long userId);
}