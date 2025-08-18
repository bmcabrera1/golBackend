package com.example.golbackend.modules.team_managment.services;

import com.example.golbackend.modules.team_managment.exception.TeamNotFoundException;
import com.example.golbackend.modules.team_managment.model.Team;
import com.example.golbackend.modules.team_managment.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team createTeam(Team team){
        return teamRepository.save(team);
    }

    public List<Team> listTeams(){
        return teamRepository.findAll();
    }

    public Team findTeamById(Long id){
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));
    }

    public Team updateTeam(Long id, Team team) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));

        if (team.getName() != null)
            existingTeam.setName(team.getName());
        if (team.getLogo() != null)
            existingTeam.setLogo(team.getLogo());
        if (team.getPresidente() != null)
            existingTeam.setPresidente(team.getPresidente());
        if (team.getMotto() != null)
            existingTeam.setMotto(team.getMotto());

        return teamRepository.save(existingTeam);
    }

    public void deleteTeam(Long id){
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));
        teamRepository.delete(existingTeam);
    }


}
