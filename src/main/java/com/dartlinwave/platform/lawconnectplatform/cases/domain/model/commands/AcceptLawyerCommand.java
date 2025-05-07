package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AcceptLawyerCommand {
        private final Long caseId;
        private final UUID lawyerId;

        public AcceptLawyerCommand(Long caseId, UUID lawyerId) {
                this.caseId = caseId;
                this.lawyerId = lawyerId;
        }
}