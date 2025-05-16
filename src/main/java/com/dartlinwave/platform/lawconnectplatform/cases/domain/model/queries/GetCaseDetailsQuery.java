package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries;

import lombok.Getter;

@Getter
public class GetCaseDetailsQuery {
        private final Long caseId;

        public GetCaseDetailsQuery(Long caseId) {
                this.caseId = caseId;
        }
}