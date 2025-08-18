package com.example.golbackend.modules.matchday.model;

import com.example.golbackend.modules.phase.model.Phase;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "matchday")
public class Matchday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matchday_id")
    private Long matchdayId;

    @ManyToOne
    @JoinColumn(name = "phase_id", nullable = false)
    private Phase phase;

    @Column(name = "matchday_number", nullable = false)
    private Integer matchdayNumber;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
}