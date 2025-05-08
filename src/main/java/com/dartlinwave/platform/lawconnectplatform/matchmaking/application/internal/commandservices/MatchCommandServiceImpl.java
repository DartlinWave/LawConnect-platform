package com.dartlinwave.platform.lawconnectplatform.matchmaking.application.internal.commandservices;

import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.aggregates.Match;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands.CreatePreMatchCommand;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands.DeclineCaseByLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands.DeclineLawyerByClientCommand;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.services.MatchCommandService;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.infrastructure.persistence.jpa.repositories.MatchRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchCommandServiceImpl implements MatchCommandService {

    private final MatchRepository matchRepository;

    public MatchCommandServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    // TODO: Implement the logic to call externals (ACL).

    @Override
    public Long handle(CreatePreMatchCommand command) {

        var match = new Match(command);

        try {
            matchRepository.findMatchByCaseIdAndLawyerId(command.caseId(), command.lawyerId())
                    .ifPresent(existingMatch -> {
                        throw new IllegalArgumentException("Match already exists for caseId");
                    });
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating match: " + e.getMessage());
        }

        return match.getId();
    }

    @Override
    public void handle(DeclineCaseByLawyerCommand command) {

    }

    @Override
    public void handle(DeclineLawyerByClientCommand command) {

    }
}
