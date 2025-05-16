package com.dartlinwave.platform.lawconnectplatform.profiles.interfaces.acl;

import java.util.Set;

/**
 * Facade interface for managing profile-related operations.
 * <p>
 * Provides methods to create and check the existence of lawyer and client profiles.
 * </p>
 */
public interface ProfilesContextFacade {

    /**
     * Creates a new lawyer profile with the provided details.
     *
     * @param name      the lawyer's first name
     * @param lastname  the lawyer's last name
     * @param dni       the lawyer's national identification number
     * @param phone     the lawyer's phone number
     * @param specialty the lawyer's specialty area
     * @param userId    the associated user ID
     * @return the ID of the created lawyer profile
     */
    Long createLawyerProfile(String name, String lastname, String dni,
                             String phone,String description, Set<String> specialty, Long userId);

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
    Long createClientProfile(String name, String lastname, String dni, String phone, Long userId);

    /**
     * Checks if a lawyer profile exists for the given user ID.
     *
     * @param userId the user ID to check
     * @return {@code true} if a lawyer profile exists, {@code false} otherwise
     */
    boolean existsLawyerByUserId(Long userId);

    /**
     * Checks if a client profile exists for the given user ID.
     *
     * @param userId the user ID to check
     * @return {@code true} if a client profile exists, {@code false} otherwise
     */
    boolean existsClientByUserId(Long userId);
}