package com.dartlinwave.platform.lawconnectplatform.matchmaking.interfaces.rest.resources;

public record MatchResource(
        Long id,
        Long caseId,
        Long lawyerId,
        String matchStatus
) {}
