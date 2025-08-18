package com.example.golbackend.modules.standing.model;

import com.example.golbackend.modules.phase.model.Phase;
import com.example.golbackend.modules.team_managment.model.Team;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "standing")
public class Standing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "standing_id")
    private Long standingId;

    @ManyToOne
    @JoinColumn(name = "phase_id", nullable = false)
    private Phase phase;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name = "matches_played")
    private int matchesPlayed = 0;

    @Column(name = "wins")
    private int wins = 0;

    @Column(name = "draws")
    private int draws = 0;

    @Column(name = "losses")
    private int losses = 0;

    @Column(name = "goals_for")
    private int goalsFor = 0;

    @Column(name = "goals_against")
    private int goalsAgainst = 0;

    @Column(name = "goal_difference")
    private int goalDifference = 0;

    @Column(name = "points")
    private int points = 0;
}
