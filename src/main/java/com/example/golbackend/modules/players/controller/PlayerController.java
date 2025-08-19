package com.example.golbackend.modules.players.controller;

import com.example.golbackend.modules.players.dto.PlayerDto;
import com.example.golbackend.modules.players.model.Player;
import com.example.golbackend.modules.players.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams/{teamId}/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping
    public ResponseEntity<?> createPlayer(@PathVariable Long teamId, @RequestBody PlayerDto playerDto) {
        try {
            Player newPlayer = playerService.createPlayer(teamId, playerDto);
            return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers(@PathVariable Long teamId) {
        return ResponseEntity.ok(playerService.getPlayersByTeam(teamId));
    }
}
