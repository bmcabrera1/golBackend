package com.example.golbackend.modules.match_report.model;

import com.example.golbackend.modules.match.model.Match;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "match_report")
public class MatchReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_report_id")
    private Long matchReportId;

    @OneToOne
    @JoinColumn(name = "match_id", nullable = false, unique = true)
    private Match match;

    @Column(name = "referee")
    private String referee;

    @Column(name = "assistant_referee_1")
    private String assistantReferee1;

    @Column(name = "assistant_referee_2")
    private String assistantReferee2;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}