package com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.aggregates;

import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands.CreatePreMatchCommand;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.valueobjects.MatchStatus;
import com.dartlinwave.platform.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
@Entity
public class Match extends AuditableAbstractAggregateRoot<Match> {
    private Long caseId;
    private Long lawyerId;

    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus;

    public Match(Long caseId, Long lawyerId, MatchStatus matchStatus) {
        this();
        this.caseId = caseId;
        this.lawyerId = lawyerId;
        this.matchStatus = matchStatus;
    }

    public Match() {
        this.caseId = null;
        this.lawyerId = null;
        this.matchStatus = MatchStatus.PENDING;
    }

    public Match(CreatePreMatchCommand command) {
        this.caseId = command.caseId();
        this.lawyerId = command.lawyerId();
        this.matchStatus = MatchStatus.PENDING;
    }

   public void accept() {
        if (matchStatus != MatchStatus.PENDING)
            throw new IllegalStateException("Match already has a status of " + matchStatus);
        this.matchStatus = MatchStatus.MATCH;
   }

   public void decline() {
        if (matchStatus != MatchStatus.PENDING)
            throw new IllegalStateException("Match already has a status of " + matchStatus);
        this.matchStatus = MatchStatus.DECLINE;
   }


}
