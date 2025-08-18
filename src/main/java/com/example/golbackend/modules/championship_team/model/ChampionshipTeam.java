package com.example.golbackend.modules.championship_team.model;

import com.example.golbackend.modules.championship_managment.model.Championship;
import com.example.golbackend.modules.team_managment.model.Team;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "championship_team")
public class ChampionshipTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "championship_team_id")
    private Long championshipTeamId;

    @ManyToOne
    @JoinColumn(name = "championship_id", nullable = false)
    private Championship championship;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
