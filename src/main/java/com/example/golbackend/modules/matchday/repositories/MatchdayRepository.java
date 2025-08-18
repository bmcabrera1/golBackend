package com.example.golbackend.modules.matchday.repositories;

import com.example.golbackend.modules.matchday.model.Matchday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchdayRepository extends JpaRepository<Matchday, Long> {
    List<Matchday> findByPhasePhaseIdOrderByMatchdayNumberAsc(Long phaseId);
}