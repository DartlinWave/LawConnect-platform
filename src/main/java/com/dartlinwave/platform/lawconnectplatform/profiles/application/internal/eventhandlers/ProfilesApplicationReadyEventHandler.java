package com.dartlinwave.platform.lawconnectplatform.profiles.application.internal.eventhandlers;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.services.LawyerSpecialtyCommandService;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.commands.SeedLawyerSpecialtiesCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.time.Instant;

/**
 * Event handler that seeds required lawyer specialties into the system when the application is ready.
 * <p>
 * Ensures that specialties are seeded only once per application lifecycle.
 * </p>
 */
@Service
public class ProfilesApplicationReadyEventHandler {

    /**
     * Service for handling lawyer specialty-related commands.
     */
    private final LawyerSpecialtyCommandService lawyerSpecialtyCommandService;

    /**
     * Logger for logging seeding events and errors.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ProfilesApplicationReadyEventHandler.class);

    /**
     * Constructs a new {@code ApplicationReadyEventHandler} with the given {@link LawyerSpecialtyCommandService}.
     *
     * @param lawyerSpecialtyCommandService the service used to handle specialty seeding commands
     */
    public ProfilesApplicationReadyEventHandler(LawyerSpecialtyCommandService lawyerSpecialtyCommandService) {
        this.lawyerSpecialtyCommandService = lawyerSpecialtyCommandService;
    }

    /**
     * Handles the {@link ApplicationReadyEvent} by seeding lawyer specialties if not already executed.
     * <p>
     * Logs the start, success, or failure of the seeding process.
     * </p>
     *
     * @param event the application ready event
     */
    @EventListener
    public void on(ApplicationReadyEvent event) {

        var applicationName = event.getApplicationContext().getId();
        Instant start = Instant.now();

        logSeedingStart(applicationName, start);

        try {
            lawyerSpecialtyCommandService.handle(new SeedLawyerSpecialtiesCommand());
            logSeedingSuccess(applicationName);
        } catch (Exception ex) {
            logSeedingFailure(applicationName, ex);
        }

    }

    /**
     * Logs that the seeding process has already been executed.
     */
    private void logAlreadyExecuted() {
        LOGGER.info("Lawyer specialty seeding already executed. Skipping.");
    }

    /**
     * Logs the start of the seeding process.
     *
     * @param applicationName the name of the application
     * @param start           the start time of the process
     */
    private void logSeedingStart(String applicationName, Instant start) {
        LOGGER.info("[{}] Starting lawyer specialty seeding process at {}", applicationName, start);
    }

    /**
     * Logs the successful completion of the seeding process.
     *
     * @param applicationName the name of the application
     */
    private void logSeedingSuccess(String applicationName) {
        LOGGER.info("[{}] Lawyer specialty seeding completed successfully at {}", applicationName, Instant.now());
    }

    /**
     * Logs the failure of the seeding process.
     *
     * @param applicationName the name of the application
     * @param ex              the exception thrown during the process
     */
    private void logSeedingFailure(String applicationName, Exception ex) {
        LOGGER.error(
                "[{}] Failed to complete lawyer specialty seeding at {}: {}", applicationName, Instant.now(), ex.getMessage(), ex);
    }
}