package com.example.golbackend.modules.team_managment.controller;

import com.example.golbackend.modules.team_managment.model.Team;
import com.example.golbackend.modules.team_managment.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/team")
public class TeamController {


    @Autowired
    private TeamService teamService;


    @PostMapping
    public ResponseEntity<Team> createTeam(@Valid @RequestBody Team team){
        Team teamCreated = teamService.createTeam(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(teamCreated);
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams(){
        List<Team> team = teamService.listTeams();
        return ResponseEntity.ok(team);
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id){
        return teamService.findTeamById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @Valid @RequestBody Team team){
        Team teamUpdated = teamService.updateTeam(id, team);
        return ResponseEntity.ok(teamUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Team> deleteTeamById(@PathVariable Long id){
        teamService.deleteTeam(id);
        return ResponseEntity.ok().build();
    }

}
