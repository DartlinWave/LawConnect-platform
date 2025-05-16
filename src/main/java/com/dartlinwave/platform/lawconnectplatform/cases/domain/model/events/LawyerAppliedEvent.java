package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.events;

import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class LawyerAppliedEvent {
    private final Long caseId;
    private final Long lawyerId;
    private final Date occurredOn;

    public LawyerAppliedEvent(Long caseId, Long lawyerId) {
        this.caseId = caseId;
        this.lawyerId = lawyerId;
        this.occurredOn = new Date();
    }
}