package com.example.golbackend.modules.phase.repositories;

import com.example.golbackend.modules.phase.model.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Long> {
    List<Phase> findByChampionshipChampionshipIdOrderByPhaseOrderAsc(Long championshipId);
}
