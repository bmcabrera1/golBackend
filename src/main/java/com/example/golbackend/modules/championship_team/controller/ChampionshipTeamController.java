package com.example.golbackend.modules.championship_team.controller;

import com.example.golbackend.modules.championship_team.services.ChampionshipTeamService;
import com.example.golbackend.modules.team_managment.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/championships/{championshipId}/teams")
public class ChampionshipTeamController {

    @Autowired
    private ChampionshipTeamService championshipTeamService;

    @PostMapping
    public ResponseEntity<?> registerTeam(@PathVariable Long championshipId, @RequestBody Map<String, Long> payload) {
        try {
            Long teamId = payload.get("teamId");
            if (teamId == null) {
                return ResponseEntity.badRequest().body("Request body must contain 'teamId'");
            }
            championshipTeamService.registerTeamInChampionship(championshipId, teamId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Team registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Team>> getRegisteredTeams(@PathVariable Long championshipId) {
        List<Team> teams = championshipTeamService.getTeamsByChampionship(championshipId);
        return ResponseEntity.ok(teams);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTeam(@PathVariable Long championshipId, @PathVariable Long id) {
        try {
            championshipTeamService.removeTeamFromChampionship(championshipId, id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}