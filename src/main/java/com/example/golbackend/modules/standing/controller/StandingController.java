package com.example.golbackend.modules.standing.controller;

import com.example.golbackend.modules.standing.model.Standing;
import com.example.golbackend.modules.standing.services.StandingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phases/{phaseId}/standings")
public class StandingController {

    @Autowired
    private StandingService standingService;

    @GetMapping
    public ResponseEntity<List<Standing>> getStandings(@PathVariable Long phaseId) {
        List<Standing> standings = standingService.getStandingsByPhase(phaseId);
        return ResponseEntity.ok(standings);
    }
}