package com.example.golbackend.modules.match_report.services;

import com.example.golbackend.modules.match.model.Match;
import com.example.golbackend.modules.match.repositories.MatchRepository;
import com.example.golbackend.modules.match_report.dto.MatchReportDto;
import com.example.golbackend.modules.match_report.model.MatchReport;
import com.example.golbackend.modules.match_report.repositories.MatchReportRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MatchReportService {

    @Autowired
    private MatchReportRepository matchReportRepository;
    @Autowired
    private MatchRepository matchRepository;

    @Transactional
    public MatchReport createOrUpdateReport(Long matchId, MatchReportDto reportDto) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + matchId));

        MatchReport report = matchReportRepository.findByMatchMatchId(matchId)
                .orElse(new MatchReport());

        report.setMatch(match);
        report.setReferee(reportDto.getReferee());
        report.setAssistantReferee1(reportDto.getAssistantReferee1());
        report.setAssistantReferee2(reportDto.getAssistantReferee2());
        report.setNotes(reportDto.getNotes());

        if (report.getCreatedAt() == null) {
            report.setCreatedAt(LocalDateTime.now());
        }

        return matchReportRepository.save(report);
    }

    public MatchReport getReportByMatchId(Long matchId) {
        return matchReportRepository.findByMatchMatchId(matchId)
                .orElseThrow(() -> new RuntimeException("Report not found for match id: " + matchId));
    }
}
