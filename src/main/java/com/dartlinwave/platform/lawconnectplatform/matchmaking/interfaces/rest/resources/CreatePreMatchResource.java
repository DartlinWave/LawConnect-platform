package com.dartlinwave.platform.lawconnectplatform.matchmaking.interfaces.rest.resources;

public record CreatePreMatchResource(
        Long caseId,
        Long lawyerId,
        String matchStatus
) {}