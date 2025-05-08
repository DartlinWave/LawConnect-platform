package com.dartlinwave.platform.lawconnectplatform.tracking.application.eventshandler;

import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.commands.SeedStatusTypeCommand;
import com.dartlinwave.platform.lawconnectplatform.tracking.domain.services.StatusTypeCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ApplicationReadyEventHandler {
    private final StatusTypeCommandService statusTypeCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(StatusTypeCommandService statusTypeCommandService) {
        this.statusTypeCommandService = statusTypeCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        LOGGER.info("Application is ready. Initializing status types...");
        var command = new SeedStatusTypeCommand();
        statusTypeCommandService.handle(command);
        LOGGER.info("Status types initialized successfully.");
    }
}
