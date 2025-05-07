package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.Specialty;
import lombok.Getter;

@Getter
public class GetCasesBySpecialtyQuery {
        private final Specialty specialty;

        public GetCasesBySpecialtyQuery(Specialty specialty) {
                this.specialty = specialty;
        }
}