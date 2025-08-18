package com.example.golbackend.modules.match.repositories;

import com.example.golbackend.modules.match.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByMatchdayMatchdayId(Long matchdayId);
    List<Match> findByMatchdayPhasePhaseIdAndStatus(Long phaseId, String status);
}