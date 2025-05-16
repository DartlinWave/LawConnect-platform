package com.dartlinwave.platform.lawconnectplatform.profiles.interfaces.rest.transform;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;
import com.dartlinwave.platform.lawconnectplatform.profiles.interfaces.rest.resources.LawyerResource;

/**
 * Assembler utility for converting {@link Lawyer} entities to {@link LawyerResource} representations.
 * <p>
 * Facilitates the transformation of domain entities into REST API resources.
 * </p>
 */
public class LawyerResourceFromEntityAssembler {

    /**
     * Converts a {@link Lawyer} entity to a {@link LawyerResource} for API responses.
     *
     * @param lawyer the lawyer entity to convert
     * @return a resource representation of the lawyer
     */
    public static LawyerResource toResourceFromEntity(Lawyer lawyer) {
        return new LawyerResource(
                lawyer.getUserId(),
                lawyer.getId(),
                lawyer.getFullName(),
                lawyer.getDni(),
                lawyer.getPhone(),
                lawyer.getDescription(),
                lawyer.getSerializedSpecialties()
        );
    }
}