package com.dartlinwave.platform.lawconnectplatform.iam.domain.model.valueobjects;

/**
 * Enum representing the different roles available in the LawConnect platform.
 * <ul>
 *    <li>ROLE_ADMIN: Administrator user with full access to the system.</li>
 *   <li>ROLE_LAWYER: Lawyer user with permissions to manage cases.</li>
 *   <li>ROLE_CUSTOMER: Customer user who can create and track cases.</li>
 * </ul>
 */
public enum Roles {
    ROLE_ADMIN,
    ROLE_LAWYER,
    ROLE_CUSTOMER,
}