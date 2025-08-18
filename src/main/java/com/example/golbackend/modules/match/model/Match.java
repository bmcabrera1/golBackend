package com.example.golbackend.modules.match.model;

import com.example.golbackend.modules.matchday.model.Matchday;
import com.example.golbackend.modules.team_managment.model.Team;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long matchId;

    @ManyToOne
    @JoinColumn(name = "matchday_id", nullable = false)
    private Matchday matchday;

    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    @Column(name = "home_goals")
    private Integer homeGoals = 0;

    @Column(name = "away_goals")
    private Integer awayGoals = 0;

    @Column(name = "status")
    private String status = "PENDING"; // PENDING, IN_PROGRESS, FINISHED

    @Column(name = "match_time")
    private LocalTime matchTime;

    @Column(name = "location")
    private String location;
}
