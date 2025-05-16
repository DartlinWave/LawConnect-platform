package com.dartlinwave.platform.lawconnectplatform.tracking.domain.services;

import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.commands.CreateStatusCommand;

public interface StatusCommandService {
    void handle(CreateStatusCommand command);
}
