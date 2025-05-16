package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.events;

import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class CaseCreatedEvent {
    private final Long caseId;
    private final Long clientId;
    private final Date occurredOn;

    public CaseCreatedEvent(Long caseId, Long clientId) {
        this.caseId = caseId;
        this.clientId = clientId;
        this.occurredOn = new Date();
    }
}