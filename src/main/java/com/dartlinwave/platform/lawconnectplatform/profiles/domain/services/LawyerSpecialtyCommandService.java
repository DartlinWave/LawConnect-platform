package com.dartlinwave.platform.lawconnectplatform.profiles.domain.services;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.commands.SeedLawyerSpecialtiesCommand;

/**
 * Service interface for handling lawyer specialty-related commands.
 * <p>
 * Provides operations to manage the seeding of lawyer specialties in the system.
 * </p>
 */
public interface LawyerSpecialtyCommandService {

    /**
     * Handles the seeding of lawyer specialties based on the provided command.
     *
     * @param command the command containing the data required to seed lawyer specialties
     */
    void handle(SeedLawyerSpecialtiesCommand command);
}