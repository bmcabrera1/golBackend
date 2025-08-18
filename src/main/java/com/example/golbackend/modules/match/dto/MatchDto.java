package com.example.golbackend.modules.match.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class MatchDto {
    private Long homeTeamId;
    private Long awayTeamId;
    private LocalTime matchTime;
    private String location;
}
