package com.example.golbackend.modules.matchday.controller;

import com.example.golbackend.modules.matchday.model.Matchday;
import com.example.golbackend.modules.matchday.services.MatchdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phases/{phaseId}/matchdays")
public class MatchdayController {

    @Autowired
    private MatchdayService matchdayService;

    @PostMapping
    public ResponseEntity<?> createMatchday(@PathVariable Long phaseId, @RequestBody Matchday matchday) {
        try {
            Matchday newMatchday = matchdayService.createMatchday(phaseId, matchday);
            return new ResponseEntity<>(newMatchday, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Matchday>> getMatchdays(@PathVariable Long phaseId) {
        List<Matchday> matchdays = matchdayService.getMatchdaysByPhase(phaseId);
        return ResponseEntity.ok(matchdays);
    }
}