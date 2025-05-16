package com.dartlinwave.platform.lawconnectplatform.iam.application.internal.outboundservices.acl;

import com.dartlinwave.platform.lawconnectplatform.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Service for interacting with the Profiles context via ACL (Anti-Corruption Layer).
 * <p>
 * Provides methods to create lawyer and client profiles by delegating to the {@link ProfilesContextFacade}.
 * </p>
 */
@Service
public class ExternalProfileService {

    private final ProfilesContextFacade profilesContextFacade;

    /**
     * Constructs a new {@code ExternalProfileService} with the required facade.
     *
     * @param profilesContextFacade the facade for profile-related operations
     */
    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    /**
     * Creates a new lawyer profile with the provided details.
     *
     * @param name        the lawyer's first name
     * @param lastname    the lawyer's last name
     * @param dni         the lawyer's national identification number
     * @param phone       the lawyer's phone number
     * @param description the lawyer's description or bio
     * @param specialty   the set of specialties for the lawyer
     * @param userId      the associated user ID
     * @return the ID of the created lawyer profile
     */
    public Long createLawyerProfile(
            String name,
            String lastname,
            String dni,
            String phone,
            String description,
            Set<String> specialty,
            Long userId) {
        return profilesContextFacade.createLawyerProfile(
                name,
                lastname,
                dni,
                phone,
                description,
                specialty,
                userId
        );
    }

    /**
     * Creates a new client profile with the provided details.
     *
     * @param name     the client's first name
     * @param lastname the client's last name
     * @param dni      the client's national identification number
     * @param phone    the client's phone number
     * @param userId   the associated user ID
     * @return the ID of the created client profile
     */
    public Long createClientProfile(
            String name,
            String lastname,
            String dni,
            String phone,
            Long userId) {
        return profilesContextFacade.createClientProfile(
                name,
                lastname,
                dni,
                phone,
                userId
        );
    }
}