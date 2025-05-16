package com.dartlinwave.platform.lawconnectplatform.matchmaking.application.internal.eventhandlers;

import com.dartlinwave.platform.lawconnectplatform.matchmaking.application.internal.outboundservices.acl.TemporaryCaseCommandService;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.events.CaseAcceptedByLawyerEvent;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.events.LawyerAcceptedByClientEvent;
import org.springframework.context.event.EventListener;

public class MatchEventHandler {
    /* TODO: Include when the external service is implemented.
private final ExternalCaseMatchmakingService caseService;

    public MatchEventHandler(ExternalCaseMatchmakingService caseService) {
        this.caseService = caseService;
    }

    @EventListener
    public void on(LawyerAcceptedByClientEvent event) {
        caseService.assignLawyer(event.getCaseId(), event.getLawyerId());
    }

    @EventListener
    public void on(CaseAcceptedByLawyerEvent event) {
        caseService.assignLawyer(event.getCaseId(), event.getLawyerId());
    }
*/

    // mock to make the code compile without the external service.

    public final TemporaryCaseCommandService caseService;

    public MatchEventHandler(TemporaryCaseCommandService caseService) {
        this.caseService = caseService;
    }

    @EventListener
    public void listensTo(LawyerAcceptedByClientEvent event) {
        caseService.assignLawyer(event.getCaseId(), event.getLawyerId());
    }

    @EventListener
    public void listensTo(CaseAcceptedByLawyerEvent event) {
        caseService.assignLawyer(event.getCaseId(), event.getLawyerId());
    }

}
