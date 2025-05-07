package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries;

import lombok.Getter;

import java.util.UUID;

@Getter
public class GetCasesByClientQuery {
        private final UUID clientId;

        public GetCasesByClientQuery(UUID clientId) {
                this.clientId = clientId;
        }
}