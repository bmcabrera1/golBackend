package com.example.golbackend.modules.tie_break_criterion.repositories;

import com.example.golbackend.modules.tie_break_criterion.model.TieBreakCriterion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TieBreakCriterionRepository extends JpaRepository<TieBreakCriterion, Long> {
    List<TieBreakCriterion> findByPhasePhaseIdOrderByPriorityOrderAsc(Long phaseId);
    void deleteByPhasePhaseId(Long phaseId);
}