package com.dartlinwave.platform.lawconnectplatform.matchmaking.application.internal.commandservices;

import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.aggregates.Match;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands.*;
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
                        throw new IllegalArgumentException("Match already exists for matchId");
                    });
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating match: " + e.getMessage());
        }

        return match.getId();
    }

    @Override
    public void handle(AcceptCaseByLawyerCommand command) {

        var match = matchRepository.findById(command.matchId())
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));

        match.accept();
        matchRepository.save(match);

        // TODO: Include external service to assign lawyer to case when the lawyer accepts the invitation.
    }

    @Override
    public void handle(AcceptLawyerByClientCommand command) {

        var match = matchRepository.findById(command.matchId())
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));

        match.accept();
        matchRepository.save(match);

        // TODO: Include external service to assign lawyer to case when the client accepts the application.
    }

    @Override
    public void handle(DeclineCaseByLawyerCommand command) {

        var match = matchRepository.findById(command.matchId())
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));

        match.decline();
        matchRepository.save(match);

        // TODO: Include external service to decline the case when the lawyer declines the invitation.
    }

    @Override
    public void handle(DeclineLawyerByClientCommand command) {

        var match = matchRepository.findById(command.matchId())
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));

        match.decline();
        matchRepository.save(match);

        // TODO: Include external service to decline the case when the client declines the application.
    }
}
