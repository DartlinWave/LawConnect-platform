package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ApplyToCaseCommand {
        private final Long caseId;
        private final UUID lawyerId;

        public ApplyToCaseCommand(Long caseId, UUID lawyerId) {
                this.caseId = caseId;
                this.lawyerId = lawyerId;
        }
}