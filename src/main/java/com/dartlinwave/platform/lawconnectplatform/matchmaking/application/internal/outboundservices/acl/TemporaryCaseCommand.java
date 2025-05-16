package com.dartlinwave.platform.lawconnectplatform.matchmaking.application.internal.outboundservices.acl;

public interface TemporaryCaseCommand {

    // mocks command from case
    void assignLawyer(Long caseId, Long lawyerId);
}
