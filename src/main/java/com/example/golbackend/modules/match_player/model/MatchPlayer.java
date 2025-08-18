package com.example.golbackend.modules.match_player.model;


import com.example.golbackend.modules.match.model.Match;
import com.example.golbackend.modules.players.model.Player;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "match_player")
public class MatchPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_player_id")
    private Long matchPlayerId;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "is_starting")
    private boolean isStarting = false;

    @Column(name = "minutes_played")
    private int minutesPlayed = 0;

    @Column(name = "goals")
    private int goals = 0;

    @Column(name = "assists")
    private int assists = 0;

    @Column(name = "yellow_cards")
    private int yellowCards = 0;

    @Column(name = "red_cards")
    private int redCards = 0;
}
