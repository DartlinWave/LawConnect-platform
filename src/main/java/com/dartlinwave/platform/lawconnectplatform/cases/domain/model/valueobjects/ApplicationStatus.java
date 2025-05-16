package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects;

/**
 * Enumeration representing the possible statuses of a case application.
 * <ul>
 *   <li>{@link #PENDING} - The application is awaiting review or decision.</li>
 *   <li>{@link #ACCEPTED} - The application has been accepted.</li>
 *   <li>{@link #REJECTED} - The application has been rejected.</li>
 * </ul>
 */
public enum ApplicationStatus {
    /** The application is awaiting review or decision. */
    PENDING,
    /** The application has been accepted. */
    ACCEPTED,
    /** The application has been rejected. */
    REJECTED
}