package com.example.golbackend.modules.championship_team.services;

import com.example.golbackend.modules.championship_managment.model.Championship;
import com.example.golbackend.modules.championship_managment.repositories.ChampionshipRepository;
import com.example.golbackend.modules.championship_team.model.ChampionshipTeam;
import com.example.golbackend.modules.championship_team.repositories.ChampionshipTeamRepository;
import com.example.golbackend.modules.team_managment.model.Team;
import com.example.golbackend.modules.team_managment.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampionshipTeamService {

    @Autowired
    private ChampionshipTeamRepository championshipTeamRepository;
    @Autowired
    private ChampionshipRepository championshipRepository;
    @Autowired
    private TeamRepository teamRepository;

    public ChampionshipTeam registerTeamInChampionship(Long championshipId, Long teamId) {
        Championship championship = championshipRepository.findById(championshipId)
                .orElseThrow(() -> new RuntimeException("Championship not found with id: " + championshipId));
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + teamId));

        if (championshipTeamRepository.findByChampionshipChampionshipIdAndTeamId(championshipId, teamId).isPresent()) {
            throw new RuntimeException("Team is already registered in this championship");
        }

        ChampionshipTeam newRegistration = new ChampionshipTeam();
        newRegistration.setChampionship(championship);
        newRegistration.setTeam(team);

        return championshipTeamRepository.save(newRegistration);
    }

    public List<Team> getTeamsByChampionship(Long championshipId) {
        List<ChampionshipTeam> registrations = championshipTeamRepository.findByChampionshipChampionshipId(championshipId);
        return registrations.stream().map(ChampionshipTeam::getTeam).collect(Collectors.toList());
    }

    public void removeTeamFromChampionship(Long championshipId, Long teamId) {
        ChampionshipTeam registration = championshipTeamRepository.findByChampionshipChampionshipIdAndTeamId(championshipId, teamId)
                .orElseThrow(() -> new RuntimeException("Team is not registered in this championship"));
        championshipTeamRepository.delete(registration);
    }
}