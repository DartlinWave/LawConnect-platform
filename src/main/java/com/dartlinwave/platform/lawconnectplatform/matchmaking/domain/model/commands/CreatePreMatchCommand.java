package com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands;

public record CreatePreMatchCommand(
        Long caseId,
        Long lawyerId
) {}