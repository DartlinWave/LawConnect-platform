package com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.transforms;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.entities.Applicant;
import com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.resources.ApplicantResource;

/**
 * Assembler for converting an {@link Applicant} entity into an {@link ApplicantResource}
 * for REST API responses.
 */
public class ApplicantResourceFromEntityAssembler {

    /**
     * Converts an {@link Applicant} entity into an {@link ApplicantResource}.
     *
     * @param applicant the applicant entity to convert
     * @return the corresponding {@code ApplicantResource}
     */
    public static ApplicantResource toResourceFromEntity(Applicant applicant) {
        return new ApplicantResource(
                applicant.getLawyerId(),
                applicant.getStatus().name(),
                applicant.getApplicationDateAsString()
        );
    }
}