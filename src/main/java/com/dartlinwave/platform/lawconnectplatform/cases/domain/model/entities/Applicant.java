package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.entities;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.ApplicationStatus;

import lombok.*;
import jakarta.persistence.*;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Entity representing an applicant (lawyer) for a case.
 * Tracks the lawyer's application status and relevant timestamps.
 */
@With
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long lawyerId;

    /**
     * The date and time when the application was submitted.
     */
    @Column(nullable = false)
    private Date applicationDate;

    /**
     * The current status of the application.
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    /**
     * Constructs an Applicant with the given application status.
     * Sets the application date to the current time.
     *
     * @param status the status of the application
     */
    public Applicant(ApplicationStatus status) {
        this.status = status;
        this.applicationDate = new Date();
    }

    /**
     * Constructs an Applicant with the given lawyer ID and application status.
     * Sets the application date to the current time.
     *
     * @param lawyerId the ID of the lawyer
     * @param status    the status of the application
     */
    public Applicant(Long lawyerId, ApplicationStatus status) {
        this.lawyerId = lawyerId;
        this.status = status;
        this.applicationDate = new Date();
    }

    /**
     * Returns a default Applicant with status PENDING and current date.
     *
     * @return a default Applicant instance
     */
    public static Applicant getDefaultApplicantStatus() {
        return new Applicant(ApplicationStatus.PENDING);
    }

    /**
     * Marks the application as accepted if it is currently pending.
     *
     * @throws IllegalStateException if the application is not pending
     */
    public void accept() {
        if (this.status != ApplicationStatus.PENDING)
            throw new IllegalStateException("Can only accept pending applications");

        this.status = ApplicationStatus.ACCEPTED;
    }

    /**
     * Marks the application as rejected if it is currently pending.
     *
     * @throws IllegalStateException if the application is not pending
     */
    public void reject() {
        if (this.status != ApplicationStatus.PENDING) {
            throw new IllegalStateException("Can only reject pending applications");
        }
        this.status = ApplicationStatus.REJECTED;
    }

    /**
     * Returns the application date as a formatted string (e.g., yyyy-MM-dd HH:mm:ss).
     *
     * @return the formatted application date string
     */
    public String getApplicationDateAsString() {
        if (this.applicationDate == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(this.applicationDate);
    }

    /**
     * Creates a new Applicant with the specified lawyer ID and default status.
     *
     * @param lawyerId the ID of the lawyer
     * @return a new Applicant instance
     */
    public static Applicant create(Long lawyerId) {
        return new Applicant(lawyerId, ApplicationStatus.PENDING);
    }
}