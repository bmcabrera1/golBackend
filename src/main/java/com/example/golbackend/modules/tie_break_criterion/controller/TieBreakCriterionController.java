package com.example.golbackend.modules.tie_break_criterion.controller;

import com.example.golbackend.modules.tie_break_criterion.dto.TieBreakCriterionDto;
import com.example.golbackend.modules.tie_break_criterion.model.TieBreakCriterion;
import com.example.golbackend.modules.tie_break_criterion.services.TieBreakCriterionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phases/{phaseId}/tie-break-criteria")
public class TieBreakCriterionController {
    @Autowired
    private TieBreakCriterionService criterionService;

    @PostMapping
    public ResponseEntity<?> setCriteria(@PathVariable Long phaseId, @RequestBody List<TieBreakCriterionDto> criteriaDtos) {
        try {
            List<TieBreakCriterion> newCriteria = criterionService.setCriteriaForPhase(phaseId, criteriaDtos);
            return ResponseEntity.ok(newCriteria);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<TieBreakCriterion>> getCriteria(@PathVariable Long phaseId) {
        List<TieBreakCriterion> criteria = criterionService.getCriteriaForPhase(phaseId);
        return ResponseEntity.ok(criteria);
    }
}
