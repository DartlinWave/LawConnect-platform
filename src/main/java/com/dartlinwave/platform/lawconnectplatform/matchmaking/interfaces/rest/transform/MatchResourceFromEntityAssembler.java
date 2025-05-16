package com.dartlinwave.platform.lawconnectplatform.matchmaking.interfaces.rest.transform;

import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.aggregates.Match;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.interfaces.rest.resources.MatchResource;

public class MatchResourceFromEntityAssembler {
    public static MatchResource toResourceFromEntity(Match entity) {
        return new MatchResource(
                entity.getId(),
                entity.getCaseId(),
                entity.getLawyerId(),
                entity.getMatchStatus().toString());
    }
}
