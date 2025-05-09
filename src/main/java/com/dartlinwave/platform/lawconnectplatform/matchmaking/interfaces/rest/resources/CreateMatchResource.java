package com.dartlinwave.platform.lawconnectplatform.matchmaking.interfaces.rest.resources;

public record CreateMatchResource (
        Long caseId,
        Long lawyerId,
        String matchStatus
) {}