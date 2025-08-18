package com.example.golbackend.modules.phase_team.model;

import com.example.golbackend.modules.phase.model.Phase;
import com.example.golbackend.modules.team_managment.model.Team;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "phase_team")
public class PhaseTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phase_team_id")
    private Long phaseTeamId;

    @ManyToOne
    @JoinColumn(name = "phase_id", nullable = false)
    private Phase phase;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name = "group_bracket_name")
    private String groupBracketName;
}

