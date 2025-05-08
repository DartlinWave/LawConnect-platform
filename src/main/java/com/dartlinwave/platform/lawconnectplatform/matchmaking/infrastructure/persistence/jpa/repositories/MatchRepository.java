package com.dartlinwave.platform.lawconnectplatform.matchmaking.infrastructure.persistence.jpa.repositories;

import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.aggregates.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findMatchByCaseIdAndLawyerId(Long caseId, Long lawyerId);

    List<Match> findAllByLawyerId(Long lawyerId);
    List<Match> findAllByCaseId(Long caseId);
}
