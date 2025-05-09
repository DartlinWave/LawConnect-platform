package com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.services;

import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.aggregates.Match;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface MatchQueryService {
   List<Match> handle (GetAllMatchesByCaseIdQuery query);
   List<Match> handle (GetAllMatchesByLawyerIdQuery query);
   List<Match> handle (GetPendingMatchesByCaseIdQuery query);
   List<Match> handle (GetPendingMatchesByLawyerIdQuery query);

    Optional<Match> handle (GetMatchByCaseIdQuery query);
    Optional<Match> handle (GetMatchByIdQuery query);
    Optional<Match> handle (GetMatchByCaseIdAndLawyerIdQuery query);
    Optional<Match> handle (GetMatchByLawyerIdQuery query);

}