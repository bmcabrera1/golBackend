package com.example.golbackend.modules.match_report.repositories;

import com.example.golbackend.modules.match_report.model.MatchReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchReportRepository extends JpaRepository<MatchReport, Long> {
    Optional<MatchReport> findByMatchMatchId(Long matchId);
}