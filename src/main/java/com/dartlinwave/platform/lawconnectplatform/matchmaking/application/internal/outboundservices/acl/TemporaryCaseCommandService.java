package com.dartlinwave.platform.lawconnectplatform.matchmaking.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;

@Service
public class TemporaryCaseCommandService implements TemporaryCaseCommand {

    @Override
    public void assignLawyer(Long caseId, Long lawyerId) {
        System.out.println("The lawyer " + lawyerId + " has been assigned to the case " + caseId);
    }
}
