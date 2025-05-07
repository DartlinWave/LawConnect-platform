package com.dartlinwave.platform.lawconnectplatform.cases.domain.services;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates.Case;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.AcceptLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.ApplyToCaseCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.CreateCaseCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.RejectLawyerCommand;

public interface CaseCommandService {
    Case handleCreateCase(CreateCaseCommand command);
    void handleApplyToCase(ApplyToCaseCommand command);
    void handleAcceptLawyer(AcceptLawyerCommand command);
    void handleRejectLawyer(RejectLawyerCommand command);
}