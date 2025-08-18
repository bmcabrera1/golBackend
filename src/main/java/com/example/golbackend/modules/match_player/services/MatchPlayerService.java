package com.example.golbackend.modules.match_player.services;

import com.example.golbackend.modules.match.model.Match;
import com.example.golbackend.modules.match.repositories.MatchRepository;
import com.example.golbackend.modules.match_player.dto.MatchPlayerDto;
import com.example.golbackend.modules.match_player.model.MatchPlayer;
import com.example.golbackend.modules.match_player.repositories.MatchPlayerRepository;
import com.example.golbackend.modules.players.model.Player;
import com.example.golbackend.modules.players.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchPlayerService {
    @Autowired
    private MatchPlayerRepository matchPlayerRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public List<MatchPlayer> addPlayersToMatch(Long matchId, List<MatchPlayerDto> playerDtos) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + matchId));

        List<MatchPlayer> matchPlayers = playerDtos.stream().map(dto -> {
            Player player = playerRepository.findById(dto.getPlayerId())
                    .orElseThrow(() -> new RuntimeException("Player not found with id: " + dto.getPlayerId()));
            MatchPlayer matchPlayer = new MatchPlayer();
            matchPlayer.setMatch(match);
            matchPlayer.setPlayer(player);
            matchPlayer.setStarting(dto.isStarting());
            matchPlayer.setMinutesPlayed(dto.getMinutesPlayed());
            matchPlayer.setGoals(dto.getGoals());
            matchPlayer.setAssists(dto.getAssists());
            matchPlayer.setYellowCards(dto.getYellowCards());
            matchPlayer.setRedCards(dto.getRedCards());
            return matchPlayer;
        }).collect(Collectors.toList());

        return matchPlayerRepository.saveAll(matchPlayers);
    }

    public List<MatchPlayer> getPlayersInMatch(Long matchId) {
        return matchPlayerRepository.findByMatchMatchId(matchId);
    }
}