package com.example.golbackend.modules.match.dto;


import lombok.Data;

@Data
public class UpdateMatchResultDto {
    private int homeGoals;
    private int awayGoals;
    private String status;
}