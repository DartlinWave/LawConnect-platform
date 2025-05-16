package com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CaseAcceptedByLawyerEvent extends ApplicationEvent {
    private final Long caseId;
    private final Long lawyerId;

    public CaseAcceptedByLawyerEvent(Object source, Long caseId, Long lawyerId) {
        super(source);
        this.caseId = caseId;
        this.lawyerId = lawyerId;
    }
}
