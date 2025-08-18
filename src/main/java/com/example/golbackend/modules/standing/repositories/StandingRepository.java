package com.example.golbackend.modules.standing.repositories;

import com.example.golbackend.modules.standing.model.Standing;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StandingRepository extends JpaRepository<Standing, Long> {
    Optional<Standing> findByPhase_PhaseIdAndTeam_Id(Long phaseId, Long teamId);
    List<Standing> findByPhasePhaseIdOrderByPointsDescGoalDifferenceDescGoalsForDesc(Long phaseId);
    List<Standing> findByPhasePhaseId(Long phaseId, Sort sort);
}