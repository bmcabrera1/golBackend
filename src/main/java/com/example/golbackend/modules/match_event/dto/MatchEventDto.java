package com.example.golbackend.modules.match_event.dto;

import lombok.Data;

@Data
public class MatchEventDto {
    private Long playerId;
    private String eventType;
    private Integer eventMinute;
    private String notes;
}
