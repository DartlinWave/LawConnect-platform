package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.Specialty;

/**
 * Command to create a new case.
 * Contains the client identifier, case title, description, and specialty.
 *
 * @param clientId    the unique identifier of the client creating the case
 * @param title       the title of the case
 * @param description the description of the case
 * @param specialty   the specialty required for the case
 */
public record CreateCaseCommand(
        Long clientId,
        String title,
        String description,
        Specialty specialty
) {
}