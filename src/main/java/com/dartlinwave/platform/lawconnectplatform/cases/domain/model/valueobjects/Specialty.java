package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects;

/**
 * Enumeration representing the different legal specialties
 * that a case may require in the LawConnect platform.
 * <ul>
 *   <li>{@link #FAMILY} - Family law</li>
 *   <li>{@link #CRIMINAL} - Criminal law</li>
 *   <li>{@link #CORPORATE} - Corporate law</li>
 *   <li>{@link #LABOR} - Labor law</li>
 *   <li>{@link #TAX} - Tax law</li>
 *   <li>{@link #CIVIL} - Civil law</li>
 *   <li>{@link #INTELLECTUAL_PROPERTY} - Intellectual property law</li>
 * </ul>
 */
public enum Specialty {
    /** Family law specialty. */
    FAMILY,
    /** Criminal law specialty. */
    CRIMINAL,
    /** Corporate law specialty. */
    CORPORATE,
    /** Labor law specialty. */
    LABOR,
    /** Tax law specialty. */
    TAX,
    /** Civil law specialty. */
    CIVIL,
    /** Intellectual property law specialty. */
    INTELLECTUAL_PROPERTY
}