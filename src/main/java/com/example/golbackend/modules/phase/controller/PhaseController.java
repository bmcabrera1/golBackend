package com.example.golbackend.modules.phase.controller;

import com.example.golbackend.modules.phase.model.Phase;
import com.example.golbackend.modules.phase.services.PhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/championships/{championshipId}/phases")
public class PhaseController {

    @Autowired
    private PhaseService phaseService;

    @PostMapping
    public ResponseEntity<?> createPhase(@PathVariable Long championshipId, @RequestBody Phase phase) {
        try {
            Phase newPhase = phaseService.createPhase(championshipId, phase);
            return new ResponseEntity<>(newPhase, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Phase>> getPhases(@PathVariable Long championshipId) {
        List<Phase> phases = phaseService.getPhasesByChampionship(championshipId);
        return ResponseEntity.ok(phases);
    }
}
