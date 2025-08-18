package com.example.golbackend.modules.match_report.controller;

import com.example.golbackend.modules.match_report.dto.MatchReportDto;
import com.example.golbackend.modules.match_report.model.MatchReport;
import com.example.golbackend.modules.match_report.services.MatchReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matches/{matchId}/report")
public class MatchReportController {

    @Autowired
    private MatchReportService matchReportService;

    @PutMapping
    public ResponseEntity<?> createOrUpdateReport(@PathVariable Long matchId, @RequestBody MatchReportDto reportDto) {
        try {
            MatchReport report = matchReportService.createOrUpdateReport(matchId, reportDto);
            return ResponseEntity.ok(report);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getReport(@PathVariable Long matchId) {
        try {
            MatchReport report = matchReportService.getReportByMatchId(matchId);
            return ResponseEntity.ok(report);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
