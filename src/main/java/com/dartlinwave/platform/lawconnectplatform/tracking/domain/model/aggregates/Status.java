package com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.aggregates;

import com.dartlinwave.platform.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.commands.CreateStatusCommand;

public class Status extends AuditableAbstractAggregateRoot <Status> {
    private long legalCaseId;

    public long getLegalCaseId() {
        return legalCaseId;
    }



    public Status(CreateStatusCommand command){
        this.legalCaseId = command.legalCaseId();
    }

    public Status() {
        super();
    }
}
