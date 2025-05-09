package com.dartlinwave.platform.lawconnectplatform.matchmaking.application.internal.commandservices;

import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.aggregates.Match;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.queries.*;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.valueobjects.MatchStatus;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.services.MatchQueryService;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.infrastructure.persistence.jpa.repositories.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchQueryServiceImpl implements MatchQueryService {
    private final MatchRepository matchRepository;

    public MatchQueryServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Match> handle(GetAllMatchesByCaseIdQuery query) {
        return matchRepository.findAllByCaseId(query.caseId());
    }

    @Override
    public List<Match> handle(GetAllMatchesByLawyerIdQuery query) {
        return matchRepository.findAllByLawyerId(query.lawyerId());
    }

    @Override
    public List<Match> handle(GetPendingMatchesByCaseIdQuery query) {
        return matchRepository.findAllByMatchStatusAndCaseId(MatchStatus.PENDING, query.caseId());
    }

    @Override
    public List<Match> handle(GetPendingMatchesByLawyerIdQuery query) {
        return matchRepository.findAllByMatchStatusAndLawyerId(MatchStatus.PENDING, query.lawyerId());
    }

    @Override
    public Optional<Match> handle(GetMatchByCaseIdQuery query) {
        return matchRepository.findByCaseId(query.caseId());
    }

    @Override
    public Optional<Match> handle(GetMatchByIdQuery query) {
        return matchRepository.findById(query.matchId());
    }

    @Override
    public Optional<Match> handle(GetMatchByCaseIdAndLawyerIdQuery query) {
        return matchRepository.findByCaseIdAndLawyerId(query.caseId(), query.lawyerId());
    }

    @Override
    public Optional<Match> handle(GetMatchByLawyerIdQuery query) {
        return matchRepository.findByLawyerId(query.lawyerId());
    }
}
