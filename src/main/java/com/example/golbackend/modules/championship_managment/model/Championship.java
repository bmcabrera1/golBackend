package com.example.golbackend.modules.championship_managment.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;



@Entity
@Table(name = "championship")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Championship {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "championship_id")
    private Long championshipId;

    @NotBlank(message = "El nombre del campeonato es obligatorio")
    @Column(name = "name")
    private String championshipName;

    @Column(name = "description")
    private String championshipDescription;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "status")
    private String championshipStatus;

    @Column(name = "min_players_field")
    private int minPlayersField;

    @Column(name = "min_players_registered")
    private int minPlayersRegistered;

    @Column(name = "max_players_registered")
    private int maxPlayersRegistered;

    @Column(name = "yellow_double_suspension_matches")
    private int yellowDoubleSuspensionMatches;

    @Column(name = "red_card_suspension_matches")
    private int redSuspensionMatches;

    @Column(name = "yellow_accumulation_suspension_matches")
    private int yellowAccumulationSuspensionMatches;

    @Column(name = "yellow_accumulation_number")
    private int yellowAccumulationNumber;

    @Column(name = "rules")
    private String rule;

    @Column(name = "first_place_prize")
    private String firstPlacePrize;

    @Column(name = "second_place_prize")
    private String secondPlacePrize;

    @Column(name = "third_place_prize")
    private String thirdPlacePrize;

    @Column(name = "other_prizes")
    private String otherPlacePrize;

    @Column(name = "points_win")
    private int pointsWin;

    @Column(name = "points_loss")
    private int pointsLose;

    @Column(name = "points_draw")
    private int pointsDraw;


}
