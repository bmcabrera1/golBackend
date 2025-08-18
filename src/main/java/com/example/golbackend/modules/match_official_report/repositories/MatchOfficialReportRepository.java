package com.example.golbackend.modules.match_official_report.repositories;

import com.example.golbackend.modules.match_official_report.model.MatchOfficialReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchOfficialReportRepository extends JpaRepository<MatchOfficialReport, Long> {
    List<MatchOfficialReport> findByMatchMatchId(Long matchId);
}