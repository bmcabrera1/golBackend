package com.example.golbackend.modules.phase.model;

import com.example.golbackend.modules.championship_managment.model.Championship;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "phase")
public class Phase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phase_id")
    private Long phaseId;

    @ManyToOne
    @JoinColumn(name = "championship_id", nullable = false)
    private Championship championship;

    @Column(name = "phase_name", nullable = false)
    private String phaseName;

    @Column(name = "phase_type", nullable = false)
    private String phaseType;

    @Column(name = "phase_order", nullable = false)
    private Integer phaseOrder;

    @Column(name = "status")
    private String status;
}
