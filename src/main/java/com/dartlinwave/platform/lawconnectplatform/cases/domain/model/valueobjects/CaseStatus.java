package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects;

    /**
     * Enumeration representing the possible statuses of a case.
     * <ul>
     *   <li>{@link #OPEN_STATUS} - The case is open and accepting applicants.</li>
     *   <li>{@link #IN_EVALUATION_STATUS} - The case is under evaluation after a lawyer has been accepted.</li>
     *   <li>{@link #ACCEPTED_STATUS} - The case has been accepted and is in progress.</li>
     *   <li>{@link #CLOSED_STATUS} - The case is closed and no further actions can be taken.</li>
     * </ul>
     */
    public enum CaseStatus {
        /** The case is open and accepting applicants. */
        OPEN_STATUS,
        /** The case is under evaluation after a lawyer has been accepted. */
        IN_EVALUATION_STATUS,
        /** The case has been accepted and is in progress. */
        ACCEPTED_STATUS,
        /** The case is closed and no further actions can be taken. */
        CLOSED_STATUS,
    }