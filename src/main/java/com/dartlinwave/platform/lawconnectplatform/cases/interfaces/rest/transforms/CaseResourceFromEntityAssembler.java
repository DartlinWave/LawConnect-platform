package com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.transforms;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates.Case;
import com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.resources.CaseResource;

import java.util.stream.Collectors;

/**
 * Assembler for converting a {@link Case} entity into a {@link CaseResource}
 * for REST API responses.
 */
public class CaseResourceFromEntityAssembler {

    /**
     * Converts a {@link Case} entity into a {@link CaseResource}.
     *
     * @param caseEntity the case entity to convert
     * @return the corresponding {@code CaseResource}
     */
    public static CaseResource toResourceFromEntity(Case caseEntity) {
        return new CaseResource(
                caseEntity.getId(),
                caseEntity.getClientId(),
                caseEntity.getTitle(),
                caseEntity.getDescription(),
                caseEntity.getRequiredSpecialty().name(),
                caseEntity.getStatus().name(),
                caseEntity.getApplicants().stream()
                        .map(ApplicantResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toSet()),
                caseEntity.getCreatedAt().toString()
        );
    }
}