package com.example.golbackend.modules.match_official_report.services;

import com.example.golbackend.modules.match.model.Match;
import com.example.golbackend.modules.match.repositories.MatchRepository;
import com.example.golbackend.modules.match_official_report.dto.MatchOfficialReportDto;
import com.example.golbackend.modules.match_official_report.model.MatchOfficialReport;
import com.example.golbackend.modules.match_official_report.repositories.MatchOfficialReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchOfficialReportService {
    @Autowired
    private MatchOfficialReportRepository reportRepository;
    @Autowired
    private MatchRepository matchRepository;

    public MatchOfficialReport createReport(Long matchId, MatchOfficialReportDto reportDto) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + matchId));

        MatchOfficialReport report = new MatchOfficialReport();
        report.setMatch(match);
        report.setRole(reportDto.getRole());
        report.setOfficialName(reportDto.getOfficialName());
        report.setReportText(reportDto.getReportText());

        return reportRepository.save(report);
    }

    public List<MatchOfficialReport> getReportsByMatch(Long matchId) {
        return reportRepository.findByMatchMatchId(matchId);
    }
}

