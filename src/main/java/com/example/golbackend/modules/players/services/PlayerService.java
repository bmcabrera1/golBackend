package com.example.golbackend.modules.players.services;

import com.example.golbackend.modules.players.dto.PlayerDto;
import com.example.golbackend.modules.players.model.Player;
import com.example.golbackend.modules.players.repositories.PlayerRepository;
import com.example.golbackend.modules.team_managment.model.Team;
import com.example.golbackend.modules.team_managment.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    public Player createPlayer(Long teamId, PlayerDto playerDto) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + teamId));
        Player player = new Player();
        player.setTeam(team);
        player.setFirstName(playerDto.getFirstName());
        player.setLastName(playerDto.getLastName());
        player.setIdCard(playerDto.getIdCard());
        player.setBirthDate(playerDto.getBirthDate());
        player.setPosition(playerDto.getPosition());
        player.setShirtNumber(playerDto.getShirtNumber());
        if (playerDto.getStatus() != null) {
            player.setStatus(playerDto.getStatus());
        }
        return playerRepository.save(player);
    }

    public List<Player> getPlayersByTeam(Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }
}