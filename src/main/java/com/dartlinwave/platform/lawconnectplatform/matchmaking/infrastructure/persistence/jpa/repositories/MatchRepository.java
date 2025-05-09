package com.dartlinwave.platform.lawconnectplatform.matchmaking.infrastructure.persistence.jpa.repositories;

import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.aggregates.Match;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.valueobjects.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByCaseIdAndLawyerId(Long caseId, Long lawyerId);
    Optional<Match> findByLawyerId(Long lawyerId);
    Optional<Match> findByCaseId(Long caseId);

    List<Match> findAllByMatchStatusAndLawyerId(MatchStatus matchStatus, Long lawyerId);
    List<Match> findAllByMatchStatusAndCaseId(MatchStatus matchStatus, Long caseId);
    List<Match> findAllByLawyerId(Long lawyerId);
    List<Match> findAllByCaseId(Long caseId);
}
