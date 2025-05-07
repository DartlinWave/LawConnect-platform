package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.events;

import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class LawyerRejectedEvent {
    private final Long caseId;
    private final UUID lawyerId;
    private final Date occurredOn;

    public LawyerRejectedEvent(Long caseId, UUID lawyerId) {
        this.caseId = caseId;
        this.lawyerId = lawyerId;
        this.occurredOn = new Date();
    }
}