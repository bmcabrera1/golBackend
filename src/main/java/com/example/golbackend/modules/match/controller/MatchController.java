package com.example.golbackend.modules.match.controller;

import com.example.golbackend.modules.match.dto.MatchDto;
import com.example.golbackend.modules.match.dto.UpdateMatchResultDto;
import com.example.golbackend.modules.match.model.Match;
import com.example.golbackend.modules.match.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/{matchdayId}/matches")
    public ResponseEntity<?> createMatch(@PathVariable Long matchdayId, @RequestBody MatchDto matchDto) {
        try {
            Match newMatch = matchService.createMatch(matchdayId, matchDto);
            return new ResponseEntity<>(newMatch, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{matchdayId}/matches")
    public ResponseEntity<List<Match>> getMatches(@PathVariable Long matchdayId) {
        List<Match> matches = matchService.getMatchesByMatchday(matchdayId);
        return ResponseEntity.ok(matches);
    }

    @PutMapping("/{matchId}")
    public ResponseEntity<?> updateMatchResult(@PathVariable Long matchId, @RequestBody UpdateMatchResultDto resultDto) {
        try {
            Match updatedMatch = matchService.updateMatchResult(matchId, resultDto);
            return ResponseEntity.ok(updatedMatch);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}