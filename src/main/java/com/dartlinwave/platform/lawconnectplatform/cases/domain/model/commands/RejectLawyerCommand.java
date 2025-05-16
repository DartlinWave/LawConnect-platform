package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RejectLawyerCommand {
        private final Long caseId;
        private final UUID lawyerId;

        public RejectLawyerCommand(Long caseId, UUID lawyerId) {
                this.caseId = caseId;
                this.lawyerId = lawyerId;
        }
}