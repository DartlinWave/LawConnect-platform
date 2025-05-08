package com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.services;

import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands.CreatePreMatchCommand;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands.DeclineCaseByLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands.DeclineLawyerByClientCommand;

public interface MatchCommandService {
    Long handle (CreatePreMatchCommand command);

    void handle (DeclineCaseByLawyerCommand command);
    void handle (DeclineLawyerByClientCommand  command);
}
