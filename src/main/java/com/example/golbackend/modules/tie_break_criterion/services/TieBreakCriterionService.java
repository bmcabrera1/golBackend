package com.example.golbackend.modules.tie_break_criterion.services;

import com.example.golbackend.modules.phase.model.Phase;
import com.example.golbackend.modules.phase.repositories.PhaseRepository;
import com.example.golbackend.modules.tie_break_criterion.dto.TieBreakCriterionDto;
import com.example.golbackend.modules.tie_break_criterion.model.TieBreakCriterion;
import com.example.golbackend.modules.tie_break_criterion.repositories.TieBreakCriterionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TieBreakCriterionService {
    @Autowired
    private TieBreakCriterionRepository criterionRepository;
    @Autowired
    private PhaseRepository phaseRepository;

    @Transactional
    public List<TieBreakCriterion> setCriteriaForPhase(Long phaseId, List<TieBreakCriterionDto> criteriaDtos) {
        Phase phase = phaseRepository.findById(phaseId)
                .orElseThrow(() -> new RuntimeException("Phase not found with id: " + phaseId));

        // Borra los criterios antiguos para reemplazarlos
        criterionRepository.deleteByPhasePhaseId(phaseId);

        List<TieBreakCriterion> newCriteria = criteriaDtos.stream()
                .map(dto -> {
                    TieBreakCriterion criterion = new TieBreakCriterion();
                    criterion.setPhase(phase);
                    criterion.setPriorityOrder(dto.getPriorityOrder());
                    criterion.setCriterion(dto.getCriterion());
                    return criterion;
                })
                .collect(Collectors.toList());

        return criterionRepository.saveAll(newCriteria);
    }

    public List<TieBreakCriterion> getCriteriaForPhase(Long phaseId) {
        return criterionRepository.findByPhasePhaseIdOrderByPriorityOrderAsc(phaseId);
    }
}