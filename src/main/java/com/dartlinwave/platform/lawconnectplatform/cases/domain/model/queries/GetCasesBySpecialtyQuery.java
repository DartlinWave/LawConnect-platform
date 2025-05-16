package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.Specialty;

/**
 * Query to retrieve all cases that require a specific specialty.
 *
 * @param specialty the specialty required for the cases to be retrieved
 */
public record GetCasesBySpecialtyQuery(Specialty specialty) {
}