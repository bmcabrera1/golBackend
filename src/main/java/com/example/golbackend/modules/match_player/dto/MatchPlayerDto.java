package com.example.golbackend.modules.match_player.dto;

import lombok.Data;

@Data
public class MatchPlayerDto {
    private Long playerId;
    private boolean isStarting;
    private int minutesPlayed;
    private int goals;
    private int assists;
    private int yellowCards;
    private int redCards;
}