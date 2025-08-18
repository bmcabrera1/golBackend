package com.example.golbackend.modules.players.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PlayerDto {
    private String firstName;
    private String lastName;
    private String idCard;
    private LocalDate birthDate;
    private String position;
    private Integer shirtNumber;
    private String status;
}