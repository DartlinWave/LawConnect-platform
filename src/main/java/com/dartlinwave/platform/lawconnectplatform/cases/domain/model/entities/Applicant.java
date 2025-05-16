package com.dartlinwave.platform.lawconnectplatform.cases.domain.model.entities;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.ApplicationStatus;
import com.dartlinwave.platform.lawconnectplatform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class Applicant extends AuditableModel {

    @Getter
    @Column(nullable = false)
    private UUID lawyerId;

    @Getter
    @Column(nullable = false)
    private Date applicationDate;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    public Applicant(UUID lawyerId) {
        this.lawyerId = lawyerId;
        this.applicationDate = new Date();
        this.status = ApplicationStatus.PENDING;
    }

    // Business logic methods
    public void accept() {
        if (this.status != ApplicationStatus.PENDING) {
            throw new IllegalStateException("Can only accept pending applications");
        }
        this.status = ApplicationStatus.ACCEPTED;
    }

    public void reject() {
        if (this.status != ApplicationStatus.PENDING) {
            throw new IllegalStateException("Can only reject pending applications");
        }
        this.status = ApplicationStatus.REJECTED;
    }

    // Factory method
    public static Applicant create(UUID lawyerId) {
        return new Applicant(lawyerId);
    }
}