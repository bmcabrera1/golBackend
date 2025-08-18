package com.example.golbackend.modules.tie_break_criterion.model;

import com.example.golbackend.modules.phase.model.Phase;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tie_break_criterion")
public class TieBreakCriterion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "criterion_id")
    private Long criterionId;

    @ManyToOne
    @JoinColumn(name = "phase_id", nullable = false)
    private Phase phase;

    @Column(name = "priority_order", nullable = false)
    private int priorityOrder;

    @Column(name = "criterion", nullable = false)
    private String criterion;
}
