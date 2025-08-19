package com.example.golbackend.modules.phase_team.controller;

import com.example.golbackend.modules.phase_team.dto.PhaseTeamDto;
import com.example.golbackend.modules.phase_team.model.PhaseTeam;
import com.example.golbackend.modules.phase_team.services.PhaseTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phases/{phaseId}/teams")
public class PhaseTeamController {
    @Autowired
    private PhaseTeamService phaseTeamService;

    @PostMapping
    public ResponseEntity<?> addTeamToPhase(@PathVariable Long phaseId, @RequestBody PhaseTeamDto phaseTeamDto) {
        try {
            PhaseTeam newPhaseTeam = phaseTeamService.addTeamToPhase(phaseId, phaseTeamDto);
            return new ResponseEntity<>(newPhaseTeam, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PhaseTeam>> getTeamsInPhase(@PathVariable Long phaseId) {
        List<PhaseTeam> teams = phaseTeamService.getTeamsInPhase(phaseId);
        return ResponseEntity.ok(teams);
    }
}
