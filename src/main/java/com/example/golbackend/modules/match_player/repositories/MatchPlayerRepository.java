package com.example.golbackend.modules.match_player.repositories;

import com.example.golbackend.modules.match_player.model.MatchPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchPlayerRepository extends JpaRepository<MatchPlayer, Long> {
    List<MatchPlayer> findByMatchMatchId(Long matchId);
}