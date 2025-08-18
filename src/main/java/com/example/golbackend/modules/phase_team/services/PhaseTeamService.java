package com.example.golbackend.modules.phase_team.services;

import com.example.golbackend.modules.phase.model.Phase;
import com.example.golbackend.modules.phase.repositories.PhaseRepository;
import com.example.golbackend.modules.phase_team.dto.PhaseTeamDto;
import com.example.golbackend.modules.phase_team.model.PhaseTeam;
import com.example.golbackend.modules.phase_team.repositories.PhaseTeamRepository;
import com.example.golbackend.modules.team_managment.model.Team;
import com.example.golbackend.modules.team_managment.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhaseTeamService {
    @Autowired
    private PhaseTeamRepository phaseTeamRepository;
    @Autowired
    private PhaseRepository phaseRepository;
    @Autowired
    private TeamRepository teamRepository;

    public PhaseTeam addTeamToPhase(Long phaseId, PhaseTeamDto phaseTeamDto) {
        Phase phase = phaseRepository.findById(phaseId)
                .orElseThrow(() -> new RuntimeException("Phase not found with id: " + phaseId));
        Team team = teamRepository.findById(phaseTeamDto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + phaseTeamDto.getTeamId()));

        PhaseTeam phaseTeam = new PhaseTeam();
        phaseTeam.setPhase(phase);
        phaseTeam.setTeam(team);
        phaseTeam.setGroupBracketName(phaseTeamDto.getGroupBracketName());

        return phaseTeamRepository.save(phaseTeam);
    }

    public List<PhaseTeam> getTeamsInPhase(Long phaseId) {
        return phaseTeamRepository.findByPhasePhaseId(phaseId);
    }
}
