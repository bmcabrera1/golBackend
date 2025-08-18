package com.example.golbackend.modules.match_report.dto;

import lombok.Data;

@Data
public class MatchReportDto {
    private String referee;
    private String assistantReferee1;
    private String assistantReferee2;
    private String notes;
}