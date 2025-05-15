package com.dartlinwave.platform.lawconnectplatform.iam.application.internal.eventhandlers;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.services.RoleCommandService;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.commands.SeedRolesCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.time.Instant;

/**
 * Event handler that seeds required roles into the system when the application is ready.
 * <p>
 * Ensures that roles are seeded only once per application lifecycle.
 * </p>
 */
@Service
public class ApplicationReadyEventHandler {

    /** Indicates whether the seeding process has already been executed. */
    private boolean executed = false;

    /** Service for handling role-related commands. */
    private final RoleCommandService roleCommandService;

    /** Logger for logging seeding events and errors. */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    /**
     * Constructs a new {@code ApplicationReadyEventHandler} with the given {@link RoleCommandService}.
     *
     * @param roleCommandService the service used to handle role seeding commands
     */
    public ApplicationReadyEventHandler(RoleCommandService roleCommandService) {
        this.roleCommandService = roleCommandService;
    }

    /**
     * Handles the {@link ApplicationReadyEvent} by seeding roles if not already executed.
     * <p>
     * Logs the start, success, or failure of the seeding process.
     * </p>
     *
     * @param event the application ready event
     */
    @EventListener
    public void on(ApplicationReadyEvent event) {
        if (executed) {
            logAlreadyExecuted();
            return;
        }

        var applicationName = event.getApplicationContext().getId();
        Instant start = Instant.now();

        logSeedingStart(applicationName, start);

        try {
            roleCommandService.handle(new SeedRolesCommand());
            logSeedingSuccess(applicationName);
        } catch (Exception ex) {
            logSeedingFailure(applicationName, ex);
        }

        executed = true;
    }

    /**
     * Logs that the seeding process has already been executed.
     */
    private void logAlreadyExecuted() {
        LOGGER.info("Role seeding already executed. Skipping.");
    }

    /**
     * Logs the start of the seeding process.
     *
     * @param applicationName the name of the application
     * @param start the start time of the process
     */
    private void logSeedingStart(String applicationName, Instant start) {
        LOGGER.info("[{}] Starting role seeding process at {}", applicationName, start);
    }

    /**
     * Logs the successful completion of the seeding process.
     *
     * @param applicationName the name of the application
     */
    private void logSeedingSuccess(String applicationName) {
        LOGGER.info("[{}] Role seeding completed successfully at {}", applicationName, Instant.now());
    }

    /**
     * Logs the failure of the seeding process.
     *
     * @param applicationName the name of the application
     * @param ex the exception thrown during the process
     */
    private void logSeedingFailure(String applicationName, Exception ex) {
        LOGGER.error(
                "[{}] Failed to complete role seeding at {}: {}", applicationName, Instant.now(), ex.getMessage(), ex);
    }
}