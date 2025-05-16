package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.entities.Applicant;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.Specialty;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.CaseStatus;
import com.dartlinwave.platform.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

/**
 * Aggregate root representing a legal case.
 * Contains information about the client, case details, required specialty, status, and applicants.
 */
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Case extends AuditableAbstractAggregateRoot<Case> {

    /**
     * The unique identifier of the client who created the case.
     */
    @NotNull(message = "Client ID cannot be null")
    @Positive(message = "Client ID must be positive")
    private Long clientId;

    /**
     * The title of the case.
     */
    @Getter
    @Column(nullable = false)
    private String title;

    /**
     * The description of the case.
     */
    @Getter
    @Column(nullable = false, length = 1000)
    private String description;

    /**
     * The specialty required for the case.
     */
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialty requiredSpecialty;

    /**
     * The current status of the case.
     */
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CaseStatus status;

    /**
     * The list of applicants (lawyers) who have applied to the case.
     */
    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id")
    private List<Applicant> applicants;

    /**
     * Constructs a new Case with the specified details.
     *
     * @param clientId         the unique identifier of the client
     * @param title            the title of the case
     * @param description      the description of the case
     * @param requiredSpecialty the specialty required for the case
     */
    public Case(Long clientId, String title, String description, Specialty requiredSpecialty) {
        this.clientId = clientId;
        this.applicants = new ArrayList<>();
        this.title = title;
        this.description = description;
        this.status = CaseStatus.OPEN_STATUS;
        this.requiredSpecialty = requiredSpecialty;
    }

    /**
     * Adds an applicant (lawyer) to the case.
     * Changes the case status to IN_EVALUATION_STATUS.
     *
     * @param applicant the applicant to add
     * @throws IllegalStateException if the lawyer has already applied to this case
     */
    public void addApplicant(Applicant applicant) {
        boolean alreadyApplied = applicants.stream()
                .anyMatch(a -> a.getLawyerId().equals(applicant.getLawyerId()));

        if (alreadyApplied) {
            throw new IllegalStateException("Lawyer has already applied to this case");
        }

        applicants.add(applicant);
        this.status = CaseStatus.IN_EVALUATION_STATUS;
        //registerEvent(new LawyerAppliedEvent(this.getId(), applicant.getLawyerId()));
    }

    /**
     * Accepts an applicant (lawyer) for the case.
     * Only allowed if the case is in OPEN_STATUS.
     * Changes the case status to ACCEPTED_STATUS.
     *
     * @param lawyerId the unique identifier of the lawyer to accept
     * @throws IllegalStateException    if the case is not open
     * @throws IllegalArgumentException if the lawyer has not applied to this case
     */
    public void acceptApplicant(Long lawyerId) {
        if (status != CaseStatus.OPEN_STATUS)
            throw new IllegalStateException("Cannot accept lawyer for a non-open case");

        Applicant applicant = findApplicant(lawyerId)
                .orElseThrow(() -> new IllegalArgumentException("Lawyer has not applied to this case"));

        applicant.accept();
        this.status = CaseStatus.ACCEPTED_STATUS;

        //registerEvent(new LawyerAcceptedEvent(this.getId(), lawyerId));
    }

    /**
     * Rejects an applicant (lawyer) for the case.
     *
     * @param lawyerId the unique identifier of the lawyer to reject
     * @throws IllegalArgumentException if the lawyer has not applied to this case
     */
    public void rejectApplicant(Long lawyerId) {
        Applicant applicant = findApplicant(lawyerId)
                .orElseThrow(() -> new IllegalArgumentException("Lawyer has not applied to this case"));

        applicant.reject();

        //registerEvent(new LawyerRejectedEvent(this.getId(), lawyerId));
    }

    /**
     * Finds an applicant by lawyer ID.
     *
     * @param lawyerId the unique identifier of the lawyer
     * @return an Optional containing the applicant if found, or empty otherwise
     */
    private Optional<Applicant> findApplicant(Long lawyerId) {
        return applicants.stream()
                .filter(a -> a.getLawyerId().equals(lawyerId))
                .findFirst();
    }

    /**
     * Factory method to create a new Case instance.
     *
     * @param clientId         the unique identifier of the client
     * @param title            the title of the case
     * @param description      the description of the case
     * @param requiredSpecialty the specialty required for the case
     * @return a new Case instance
     */
    public static Case create(Long clientId, String title, String description, Specialty requiredSpecialty) {
        //newCase.registerEvent(new CaseCreatedEvent(newCase.getId(), clientId));
        return new Case(clientId, title, description, requiredSpecialty);
    }
}