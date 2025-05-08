package com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.services;

import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands.*;

public interface MatchCommandService {
    Long handle (CreatePreMatchCommand command);

    void handle (AcceptCaseByLawyerCommand command);
    void handle (AcceptLawyerByClientCommand command);
    void handle (DeclineCaseByLawyerCommand command);
    void handle (DeclineLawyerByClientCommand  command);
}
