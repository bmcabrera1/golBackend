package com.example.golbackend.modules.match_official_report.model;

import com.example.golbackend.modules.match.model.Match;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "match_official_report")
public class MatchOfficialReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "official_report_id")
    private Long officialReportId;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @Column(name = "role", nullable = false)
    private String role; // 'VOCAL', 'VEEDOR', 'ARBITRO'

    @Column(name = "official_name", nullable = false)
    private String officialName;

    @Column(name = "report_text", nullable = false)
    private String reportText;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
