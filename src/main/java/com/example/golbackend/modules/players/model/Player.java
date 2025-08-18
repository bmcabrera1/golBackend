package com.example.golbackend.modules.players.model;


import com.example.golbackend.modules.team_managment.model.Team;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long playerId;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "id_card", unique = true)
    private String idCard;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "position")
    private String position;

    @Column(name = "shirt_number")
    private Integer shirtNumber;

    @Column(name = "status")
    private String status = "ACTIVE";
}
