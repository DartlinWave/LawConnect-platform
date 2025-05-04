package com.dartlinwave.platform.lawconnectplatform.tracking.domain.services;

import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.commands.SeedStatusTypeCommand;

public interface StatusTypeCommandService {
    void handle(SeedStatusTypeCommand command);
}
