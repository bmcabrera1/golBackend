package com.example.golbackend.modules.match_player.controller;

import com.example.golbackend.modules.match_player.dto.MatchPlayerDto;
import com.example.golbackend.modules.match_player.model.MatchPlayer;
import com.example.golbackend.modules.match_player.services.MatchPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches/{matchId}/players")
public class MatchPlayerController {
    @Autowired
    private MatchPlayerService matchPlayerService;

    @PostMapping
    public ResponseEntity<?> addPlayersToMatch(@PathVariable Long matchId, @RequestBody List<MatchPlayerDto> playerDtos) {
        try {
            List<MatchPlayer> matchPlayers = matchPlayerService.addPlayersToMatch(matchId, playerDtos);
            return new ResponseEntity<>(matchPlayers, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<MatchPlayer>> getPlayersInMatch(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchPlayerService.getPlayersInMatch(matchId));
    }
}