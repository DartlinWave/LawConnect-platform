package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.Specialty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateCaseCommand {
        private final UUID clientId;
        private final String title;
        private final String description;
        private final Specialty specialty;

        public CreateCaseCommand(UUID clientId, String title, String description, Specialty specialty) {
                this.clientId = clientId;
                this.title = title;
                this.description = description;
                this.specialty = specialty;
        }
}