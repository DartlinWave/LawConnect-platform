package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.entities.Applicant;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.events.CaseCreatedEvent;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.events.LawyerAcceptedEvent;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.events.LawyerAppliedEvent;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.events.LawyerRejectedEvent;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.CaseStatus;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.Specialty;
import com.dartlinwave.platform.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class Case extends AuditableAbstractAggregateRoot<Case> {

    @Getter
    @Column(nullable = false)
    private UUID clientId;

    @Getter
    @Column(nullable = false)
    private String title;

    @Getter
    @Column(nullable = false, length = 1000)
    private String description;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialty specialtyRequired;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CaseStatus status;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "case_id")
    private List<Applicant> applicants;

    public Case(UUID clientId, String title, String description, Specialty specialtyRequired) {
        this.clientId = clientId;
        this.title = title;
        this.description = description;
        this.specialtyRequired = specialtyRequired;
        this.status = CaseStatus.OPEN;
        this.applicants = new ArrayList<>();
    }

    // Business logic methods
    public void addApplicant(Applicant applicant) {
        // Check if lawyer has already applied
        boolean alreadyApplied = applicants.stream()
                .anyMatch(a -> a.getLawyerId().equals(applicant.getLawyerId()));

        if (alreadyApplied) {
            throw new IllegalStateException("Lawyer has already applied to this case");
        }

        applicants.add(applicant);
        registerEvent(new LawyerAppliedEvent(this.getId(), applicant.getLawyerId()));
    }

    public void acceptLawyer(UUID lawyerId) {
        if (status != CaseStatus.OPEN) {
            throw new IllegalStateException("Cannot accept lawyer for a non-open case");
        }

        Applicant applicant = findApplicant(lawyerId)
                .orElseThrow(() -> new IllegalArgumentException("Lawyer has not applied to this case"));

        applicant.accept();
        this.status = CaseStatus.ASSIGNED;

        registerEvent(new LawyerAcceptedEvent(this.getId(), lawyerId));
    }

    public void rejectLawyer(UUID lawyerId) {
        Applicant applicant = findApplicant(lawyerId)
                .orElseThrow(() -> new IllegalArgumentException("Lawyer has not applied to this case"));

        applicant.reject();

        registerEvent(new LawyerRejectedEvent(this.getId(), lawyerId));
    }

    private Optional<Applicant> findApplicant(UUID lawyerId) {
        return applicants.stream()
                .filter(a -> a.getLawyerId().equals(lawyerId))
                .findFirst();
    }

    // Factory method
    public static Case create(UUID clientId, String title, String description, Specialty specialtyRequired) {
        Case newCase = new Case(clientId, title, description, specialtyRequired);
        newCase.registerEvent(new CaseCreatedEvent(newCase.getId(), clientId));
        return newCase;
    }
}