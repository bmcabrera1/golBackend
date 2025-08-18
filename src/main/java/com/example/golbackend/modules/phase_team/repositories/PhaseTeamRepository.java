package com.example.golbackend.modules.phase_team.repositories;

import com.example.golbackend.modules.phase_team.model.PhaseTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhaseTeamRepository extends JpaRepository<PhaseTeam, Long> {
    List<PhaseTeam> findByPhasePhaseId(Long phaseId);
}