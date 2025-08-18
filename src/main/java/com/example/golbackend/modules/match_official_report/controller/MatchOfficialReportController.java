package com.example.golbackend.modules.match_official_report.controller;

import com.example.golbackend.modules.match_official_report.dto.MatchOfficialReportDto;
import com.example.golbackend.modules.match_official_report.model.MatchOfficialReport;
import com.example.golbackend.modules.match_official_report.services.MatchOfficialReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches/{matchId}/official-reports")
public class MatchOfficialReportController {
    @Autowired
    private MatchOfficialReportService reportService;

    @PostMapping
    public ResponseEntity<?> createReport(@PathVariable Long matchId, @RequestBody MatchOfficialReportDto reportDto) {
        try {
            MatchOfficialReport newReport = reportService.createReport(matchId, reportDto);
            return new ResponseEntity<>(newReport, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<MatchOfficialReport>> getReports(@PathVariable Long matchId) {
        return ResponseEntity.ok(reportService.getReportsByMatch(matchId));
    }
}
